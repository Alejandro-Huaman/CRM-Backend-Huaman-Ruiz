package com.example.crm.backend.resource.Sales;

import com.example.crm.backend.domain.salesAggregate.model.entity.Customer;
import com.example.crm.backend.domain.userAggregate.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateSalesResource {
    private String name;

    private Date finishdate;

    private String amount;
}
