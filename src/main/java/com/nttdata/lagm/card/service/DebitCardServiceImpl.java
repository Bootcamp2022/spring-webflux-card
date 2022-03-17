package com.nttdata.lagm.card.service;

import com.nttdata.lagm.card.dto.DebitCardRequestDto;
import com.nttdata.lagm.card.model.DebitCard;
import com.nttdata.lagm.card.model.account.BankAccount;
import com.nttdata.lagm.card.proxy.AccountProxy;
import com.nttdata.lagm.card.proxy.CustomerProxy;
import com.nttdata.lagm.card.repository.DebitCardRepository;
import com.nttdata.lagm.card.util.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class DebitCardServiceImpl implements DebitCardService {

    private Logger LOGGER = LoggerFactory.getLogger(DebitCardServiceImpl.class);

    @Autowired
    private DebitCardRepository debitCardRepository;

    @Autowired
    private CustomerProxy customerProxy;

    @Autowired
    private AccountProxy accountProxy;

    @Override
    public Mono<DebitCard> create(DebitCardRequestDto debitCardRequestDto) {
        DebitCard debitCard = Converter.convertToDebitCard(debitCardRequestDto);
        LOGGER.info("DebitCardServiceImpl - Creating debitCard: {}", debitCard);
        return
            checkConditions(debitCard)
            .then(debitCardRepository.save(debitCard));
    }

    @Override
    public Flux<DebitCard> findAll() {
        return debitCardRepository.findAll();
    }

    @Override
    public Mono<DebitCard> findById(String id) {
        return debitCardRepository.findById(id);
    }

    @Override
    public Mono<DebitCard> delete(String id) {
        return debitCardRepository.findById(id).flatMap(debitCard -> {
            debitCard.setStatus(false);
            return debitCardRepository.save(debitCard);
        });
    }

    private Mono<Void> checkConditions(DebitCard debitCard) {
        return checkCardNumberNotExist(debitCard.getCardNumber())
            .mergeWith(checkIfCustomerExist(debitCard))
            .mergeWith(checkIfBankAccountsBelongToCustomer(debitCard))
            .then();
    }

    private Mono<Void> checkIfCustomerExist(DebitCard debitCard) {
        String dni = debitCard.getCustomer().getDni();
        return customerProxy.findByDni(dni)
            .switchIfEmpty(Mono.error(new Exception("El cliente con dni: " + dni + " no existe")))
            .flatMap(customer -> {
                debitCard.setCustomer(customer);
                return Mono.empty();
            });
    }

    private Flux<Void> checkIfBankAccountsBelongToCustomer(DebitCard debitCard) {
        LOGGER.info("checkIfBankAccountsBelongToCustomer");
        List<BankAccount> bankAccountList = new ArrayList<>();
        bankAccountList.add(debitCard.getMainBankAccount());
        bankAccountList.addAll(debitCard.getAditionalBankAccounts());
        String dni = debitCard.getCustomer().getDni();
        LOGGER.info("dni: {}, bankAccountList: {}", dni, bankAccountList);

        return Flux.fromIterable(bankAccountList)
            .flatMap(bankAccount -> {
                String accountNumber = bankAccount.getAccountNumber();
                LOGGER.info("accountNumber: {}", accountNumber);
                return accountProxy.findAllByAccountNumberAndDni(accountNumber, dni)
                    .switchIfEmpty(Mono.error(new Exception("El numero de cuenta " + accountNumber + " no pertenece al cliente con dni: " + dni)))
                    .flatMap(account -> {
                        LOGGER.info("Founded account: {}", account);
                        bankAccount.setId(account.getId());
                        bankAccount.setCci(account.getCci());
                        bankAccount.setCustomer(account.getCustomer());
                        bankAccount.setAmount(account.getAmount());
                        bankAccount.setStatus(account.getStatus());
                        bankAccount.setBankAccountType(account.getBankAccountType());
                        bankAccount.setMaintenanceFee(account.getMaintenanceFee());
                        bankAccount.setMaxLimitMonthlyMovements(account.getMaxLimitMonthlyMovements());
                        bankAccount.setDayAllowed(account.getDayAllowed());
                        return Mono.empty();
                    });
            });
    }

    private Mono<Void> checkCardNumberNotExist(String cardNumber) {
        return debitCardRepository.findAll()
            .filter(debitCard -> debitCard.getCardNumber().equals(cardNumber))
            .flatMap(debitCard -> {
                return Mono.error(new Exception("Tarjeta de débito con número: " + cardNumber + " ya existe"));
            }).then();
    }
}
