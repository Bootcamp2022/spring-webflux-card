package com.nttdata.lagm.card.model.account;

import lombok.Data;
import lombok.ToString;

@Data
public class BankAccount extends BankProduct {
    private BankAccountType bankAccountType;
    private String maintenanceFee;
    private Integer maxLimitMonthlyMovements;
    private Integer dayAllowed;
}
