package com.nttdata.lagm.card.model.customer;

import lombok.Data;

@Data
public class Customer {
    private String id;
    private String lastName;
    private String firstName;
    private String dni;
    private String phone;
    private String email;
    private Integer customerTypeId;
    private Integer customerProfileId = 1;
}
