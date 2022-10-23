package com.example.crm.backend.resource.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCustomerResource {
    private String name;

    private String lastname;

    private String email;

    private String phone;

    private String rut;
}
