package com.nttdata.lagm.card.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class DebitCardRequestDto {
    private String id;
    private String cardNumber;
    private String cvv;
    private String expiryDate;
    private String dni;

    @NotEmpty(message = "Debe ingresar obligatoriamente un numero de cuenta bancaria principal")
    private String mainNumberAccount;

    private List<String> aditionalNumberAccounts;
    private boolean status = true;
}
