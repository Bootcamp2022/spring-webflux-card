package com.nttdata.lagm.card.dto;

import lombok.Data;

import java.util.List;

@Data
public class DebitCardRequestDto {
    private String cardNumber;
    private String cvv;
    private String expiryDate;
    private List<String> listAccountNumbers;
    private boolean status = true;
}
