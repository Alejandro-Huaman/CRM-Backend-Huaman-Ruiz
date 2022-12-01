package com.example.crm.backend.service;

import com.example.crm.backend.domain.activityAggregate.persistence.TaskRepository;
import com.example.crm.backend.domain.activityAggregate.service.TaskService;
import com.example.crm.backend.domain.salesAggregate.model.entity.Customer;
import com.example.crm.backend.domain.salesAggregate.persistence.CustomerRepository;
import com.example.crm.backend.domain.salesAggregate.service.CustomerService;
import com.example.crm.shared.exception.ResourceNotFoundException;
import com.example.crm.shared.exception.ResourcePerzonalized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final String ENTITY = "Customer";

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerServiceImpl() {

    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> getCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, customerId));
    }

    @Override
    public List<Customer> getCustomerByName(String name) {
        return customerRepository.findByName(name);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if(customerRepository.existsByEmail(customer.getEmail()))
            throw  new ResourcePerzonalized("Ya existe este email");

        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer request) {
        return customerRepository.findById(customerId).map(post->{
            post.setName(request.getName());
            post.setBusinessname(request.getBusinessname());
            post.setEmail(request.getEmail());
            post.setRuc(request.getRuc());
            post.setFiscaladdress(request.getFiscaladdress());
            customerRepository.save(post);
            return post;
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, customerId));
    }

    @Override
    public ResponseEntity<?> deleteCustomer(Long customerId) {
        return customerRepository.findById(customerId).map(post -> {
            customerRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, customerId));
    }
}
