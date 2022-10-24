package com.example.crm.backend.api;

import com.example.crm.backend.domain.salesAggregate.service.CustomerService;
import com.example.crm.backend.domain.salesAggregate.service.SalesService;
import com.example.crm.backend.mapping.CustomerMapper;
import com.example.crm.backend.mapping.SalesMapper;
import com.example.crm.backend.resource.Customer.CreateCustomerResource;
import com.example.crm.backend.resource.Customer.CustomerResource;
import com.example.crm.backend.resource.Customer.UpdateCustomerResource;
import com.example.crm.backend.resource.Sales.CreateSalesResource;
import com.example.crm.backend.resource.Sales.SalesResource;
import com.example.crm.backend.resource.Sales.UpdateStatusSalesResource;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SalesController {
    @Autowired
    private SalesService salesService;

    @Autowired
    private SalesMapper mapper;

    @ApiOperation(value = "Get all Sales",notes = "Esta consulta consiste en obtener todas las ventas")
    @GetMapping("/sales")
    public Page<SalesResource> getAllSales(Pageable pageable) {
        return mapper.modelListToPage(salesService.getSales(), pageable);
    }
    @ApiOperation(value = "Get a Sale by ID",notes = "Esta consulta consiste en obtener una venta segun su ID")
    @GetMapping("/sales/{saleId}")
    public SalesResource getSalesById(@PathVariable("saleId") Long saleId) {
        return mapper.toResource(salesService.getSaleById(saleId));
    }
    @ApiOperation(value = "Get a Sale by Name",notes = "Esta consulta consiste en obtener una venta segun su estado")
    @GetMapping("/status/{statusId}/sales")
    public Page<SalesResource> getSalesByStatusId(@PathVariable("statusId") Long statusId,Pageable pageable) {
        return mapper.modelListToPage(salesService.getSaleByStatus(statusId),pageable);
    }

    @ApiOperation(value = "Create a Sale",notes = "Esta consulta consiste en crear una venta")
    @PostMapping("/user/{userId}/customer/{customerId}/sales")
    public SalesResource createSale(@PathVariable Long userId,@PathVariable Long customerId, @RequestBody CreateSalesResource request) {
        return mapper.toResource(salesService.createSale(userId,customerId,mapper.toModel(request)));
    }

    @ApiOperation(value = "Update a status of a Sale",notes = "Esta consulta consiste en actualizar un estado de una venta")
    @PutMapping("/sales/{saleId}/status")
    public SalesResource updateStatusSale(@PathVariable Long saleId, @RequestBody UpdateStatusSalesResource request) {
        return mapper.toResource(salesService.updateStatusSales(saleId, mapper.toModel(request)));
    }

    @ApiOperation(value = "Delete a Sale",notes = "Esta consulta consiste en eliminar una venta")
    @DeleteMapping("/sales/{saleId}")
    public ResponseEntity<?> deleteSale(@PathVariable Long saleId) {
        return salesService.deleteSale(saleId);
    }
}
