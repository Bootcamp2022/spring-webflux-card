package com.nttdata.lagm.card.model.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class Customer implements Serializable {
    private String id;
    private String lastName;
    private String firstName;
    private String dni;
    private String phone;
    private String email;
    private Integer customerTypeId;
    private Integer customerProfileId = 1;
}
