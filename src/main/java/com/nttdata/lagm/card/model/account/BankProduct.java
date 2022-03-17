package com.nttdata.lagm.card.model.account;

import com.nttdata.lagm.card.model.customer.Customer;
import lombok.Data;
import lombok.ToString;

@Data
public class BankProduct {
        private String id;
        private String accountNumber;
        private String cci;
        private Customer customer;
        private String amount;
        private Boolean status = true;
}
