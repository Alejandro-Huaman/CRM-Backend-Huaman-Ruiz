package com.example.crm.backend.mapping;
import com.example.crm.backend.domain.salesAggregate.model.entity.Customer;
import com.example.crm.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.io.Serializable;
import java.util.List;

import com.example.crm.backend.resource.Customer.*;

public class CustomerMapper implements Serializable{

    @Autowired
    EnhancedModelMapper mapper;

    public CustomerResource toResource(Customer model) {
        return mapper.map(model, CustomerResource.class);
    }

    public Page<CustomerResource> modelListToPage(List<Customer> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, CustomerResource.class), pageable, modelList.size());
    }
    public Customer toModel(CreateCustomerResource resource) {
        return mapper.map(resource, Customer.class);
    }

    public Customer toModel(UpdateCustomerResource resource) {
        return mapper.map(resource, Customer.class);
    }

}
