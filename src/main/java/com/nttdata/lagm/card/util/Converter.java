package com.nttdata.lagm.card.util;

import com.nttdata.lagm.card.dto.DebitCardRequestDto;
import com.nttdata.lagm.card.model.DebitCard;
import com.nttdata.lagm.card.model.account.BankAccount;
import com.nttdata.lagm.card.model.customer.Customer;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static DebitCard convertToDebitCard(DebitCardRequestDto debitCardRequestDto) {
        DebitCard debitCard = new DebitCard();
        debitCard.setCardNumber(debitCardRequestDto.getCardNumber());
        debitCard.setCvv(debitCardRequestDto.getCvv());
        debitCard.setExpiryDate(debitCardRequestDto.getExpiryDate());
        Customer customer = new Customer();
        customer.setDni(debitCardRequestDto.getDni());
        debitCard.setCustomer(customer);

        BankAccount mainBankAccount = new BankAccount();
        mainBankAccount.setAccountNumber(debitCardRequestDto.getMainNumberAccount());
        debitCard.setMainBankAccount(mainBankAccount);

        List<BankAccount> bankAccountList = new ArrayList<>();
        List<String> aditionalNumberAccounts = debitCardRequestDto.getAditionalNumberAccounts();
        if (aditionalNumberAccounts != null) {
            aditionalNumberAccounts.stream()
                .filter(accountNumber ->
                    !accountNumber.equals(debitCardRequestDto.getMainNumberAccount()) &&
                    StringUtils.isNotBlank(accountNumber))
                .forEach(
                    accountNumber -> {
                        BankAccount bankAccount = new BankAccount();
                        bankAccount.setAccountNumber(accountNumber);
                        bankAccountList.add(bankAccount);
                    }
                );
        }
        debitCard.setAditionalBankAccounts(bankAccountList);
        return debitCard;
    }
}
