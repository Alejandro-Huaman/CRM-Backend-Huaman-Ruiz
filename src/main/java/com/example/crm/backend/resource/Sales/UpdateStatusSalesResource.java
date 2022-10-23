package com.example.crm.backend.resource.Sales;

import com.example.crm.backend.domain.salesAggregate.model.enumeration.StatusName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStatusSalesResource {
    private StatusName statusname;
}
