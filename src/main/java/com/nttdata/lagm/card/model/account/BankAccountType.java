package com.nttdata.lagm.card.model.account;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class BankAccountType {
    private Integer id;
    private String description;
    private String commision;
}
