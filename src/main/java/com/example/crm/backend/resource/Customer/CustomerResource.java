package com.example.crm.backend.resource.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResource {

    private Long id;

    private String name;

    private String lastname;

    private String email;

    private String phone;

    private String rut;
}
