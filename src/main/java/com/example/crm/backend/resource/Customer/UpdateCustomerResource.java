package com.example.crm.backend.resource.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCustomerResource {

    private String businessname;

    private String ruc;

    private String fiscaladdress;

    private String email;
}
