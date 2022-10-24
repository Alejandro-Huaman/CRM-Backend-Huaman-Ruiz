package com.example.crm.backend.resource.Sales;

import com.example.crm.backend.domain.salesAggregate.model.enumeration.StatusName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class UpdateStatusSalesResource {
    @Enumerated(EnumType.STRING)
    private StatusName statusname;
}
