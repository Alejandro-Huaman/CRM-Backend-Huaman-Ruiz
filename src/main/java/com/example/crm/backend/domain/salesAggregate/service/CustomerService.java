package com.example.crm.backend.domain.salesAggregate.service;

import com.example.crm.backend.domain.activityAggregate.model.entity.Task;
import com.example.crm.backend.domain.salesAggregate.model.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers();
    Page<Customer> getCustomers(Pageable pageable);
    Customer getCustomerById(Long customerId);
    Customer getCustomerByName(String name);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long customerId, Customer request);
    ResponseEntity<?> deleteCustomer(Long customerId);

}
