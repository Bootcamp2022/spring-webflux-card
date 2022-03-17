package com.nttdata.lagm.card.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.nttdata.lagm.card.model.account.BankAccount;
import com.nttdata.lagm.card.model.customer.Customer;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "debitCard")
@ToString
public class DebitCard {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String cardNumber;
    private String cvv;
    private String expiryDate;
    private Customer customer;
    private BankAccount mainBankAccount;
    private List<BankAccount> aditionalBankAccounts;
    private boolean status = true;
}
