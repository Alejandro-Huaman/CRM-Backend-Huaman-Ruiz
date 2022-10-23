package com.example.crm.backend.mapping;

import com.example.crm.backend.domain.salesAggregate.model.entity.Customer;
import com.example.crm.backend.domain.salesAggregate.model.entity.Sales;
import com.example.crm.backend.resource.Customer.CreateCustomerResource;
import com.example.crm.backend.resource.Customer.CustomerResource;
import com.example.crm.backend.resource.Customer.UpdateCustomerResource;
import com.example.crm.backend.resource.Sales.CreateSalesResource;
import com.example.crm.backend.resource.Sales.SalesResource;
import com.example.crm.backend.resource.Sales.UpdateStatusSalesResource;
import com.example.crm.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class SalesMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public SalesResource toResource(Sales model) {
        return mapper.map(model, SalesResource.class);
    }

    public Page<SalesResource> modelListToPage(List<Sales> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, SalesResource.class), pageable, modelList.size());
    }
    public Sales toModel(CreateSalesResource resource) {
        return mapper.map(resource, Sales.class);
    }

    public Sales toModel(UpdateStatusSalesResource resource) {
        return mapper.map(resource, Sales.class);
    }

}
