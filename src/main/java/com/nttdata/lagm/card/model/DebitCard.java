package com.nttdata.lagm.card.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.nttdata.lagm.card.model.account.BankAccount;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "debitCard")
@ToString
public class DebitCard implements Serializable {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String cardNumber;
    private String cvv;
    private String expiryDate;
    private List<BankAccount> bankAccounts;
    private boolean status = true;
}
