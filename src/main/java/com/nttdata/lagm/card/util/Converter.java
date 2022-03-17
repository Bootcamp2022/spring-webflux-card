package com.nttdata.lagm.card.util;

import com.nttdata.lagm.card.dto.DebitCardRequestDto;
import com.nttdata.lagm.card.model.DebitCard;
import com.nttdata.lagm.card.model.account.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static DebitCard convertToDebitCard(DebitCardRequestDto debitCardRequestDto) {
        DebitCard debitCard = new DebitCard();
        debitCard.setCardNumber(debitCardRequestDto.getCardNumber());
        debitCard.setCvv(debitCardRequestDto.getCvv());
        debitCard.setExpiryDate(debitCardRequestDto.getExpiryDate());

        List<BankAccount> bankAccountList = new ArrayList<>();
        debitCardRequestDto.getListAccountNumbers().forEach(
            accountNumber -> {
                BankAccount bankAccount = new BankAccount();
                bankAccount.setAccountNumber(accountNumber);
                bankAccountList.add(bankAccount);
            }
        );
        debitCard.setBankAccounts(bankAccountList);
        return debitCard;
    }
}
