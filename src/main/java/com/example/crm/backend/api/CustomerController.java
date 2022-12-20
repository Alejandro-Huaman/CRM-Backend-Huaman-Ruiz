package com.example.crm.backend.api;

import com.example.crm.backend.domain.salesAggregate.service.CustomerService;
import com.example.crm.backend.mapping.CustomerMapper;
import com.example.crm.backend.resource.Customer.CreateCustomerResource;
import com.example.crm.backend.resource.Customer.CustomerResource;
import com.example.crm.backend.resource.Customer.UpdateCustomerResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper mapper;

    @ApiOperation(value = "Get all customers",notes = "Esta consulta consiste en obtener a todos los clientes")
    @GetMapping
    public Page<CustomerResource> getAllCustomers(Pageable pageable) {
        return mapper.modelListToPage(customerService.getCustomers(), pageable);
    }
    @ApiOperation(value = "Get a Customer by ID",notes = "Esta consulta consiste en obtener un cliente segun su ID")
    @GetMapping("{customerId}")
    public CustomerResource getCustomerById(@PathVariable("customerId") Long customerId) {
        return mapper.toResource(customerService.getCustomerById(customerId));
    }
    @ApiOperation(value = "Get a Customer by Businessname",notes = "Esta consulta consiste en obtener un cliente segun su razon social")
    @GetMapping("/businessname/{businessname}")
    public CustomerResource getCustomerByBusinessname(@PathVariable("businessname") String businessname) {
        return mapper.toResource(customerService.getCustomerByBusinessName(businessname));
    }

    @ApiOperation(value = "Create a Customer",notes = "Esta consulta consiste en crear a un cliente")
    @PostMapping
    public CustomerResource createCustomer(@RequestBody CreateCustomerResource request) {
        return mapper.toResource(customerService.createCustomer(mapper.toModel(request)));
    }

    @ApiOperation(value = "Update a Customer",notes = "Esta consulta consiste en actualizar la informacion de un cliente")
    @PutMapping("{customerId}")
    public CustomerResource updateCustomer(@PathVariable Long customerId, @RequestBody UpdateCustomerResource request) {
        return mapper.toResource(customerService.updateCustomer(customerId, mapper.toModel(request)));
    }

    @ApiOperation(value = "Delete a Customer",notes = "Esta consulta consiste en eliminar un cliente")
    @DeleteMapping("{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
        return customerService.deleteCustomer(customerId);
    }
}
