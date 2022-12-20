package com.example.crm.backend.resource.Sales;

import com.example.crm.backend.domain.salesAggregate.model.entity.Customer;
import com.example.crm.backend.domain.salesAggregate.model.enumeration.StatusName;
import com.example.crm.backend.domain.userAggregate.model.entity.User;
import com.example.crm.backend.resource.Customer.CustomerResource;
import com.example.crm.backend.resource.User.UserResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class SalesResource {

    private Long id;

    private String name;

    private UserResource user;

    private CustomerResource customer;

    private Date finishdate;

    private String amount;

    private StatusName statusname;

    private Date created_at;

    private String typecoin;
}
