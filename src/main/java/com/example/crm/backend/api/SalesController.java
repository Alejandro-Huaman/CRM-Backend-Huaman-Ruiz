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

import java.text.ParseException;
import java.util.Date;

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
    @ApiOperation(value = "Get a Sale by User ID",notes = "Esta consulta consiste en obtener una venta segun el ID del usuario")
    @GetMapping("/sales/user/{userId}")
    public Page<SalesResource> getSalesByUserId(@PathVariable("userId") Long userId, Pageable pageable) {
        return mapper.modelListToPage(salesService.getSaleByUserId(userId),pageable);
    }
    @ApiOperation(value = "Get a Sale by Month",notes = "Esta consulta consiste en obtener una venta segun su mes")
    @GetMapping("/sales/month/{month}")
    public Page<SalesResource> getSalesByMonth(@PathVariable("month") String month, Pageable pageable) {
        return mapper.modelListToPage(salesService.getSaleByMonth(Integer.parseInt(month)),pageable);
    }
    @ApiOperation(value = "Get a Sale by Year",notes = "Esta consulta consiste en obtener una venta segun su año")
    @GetMapping("/sales/year/{year}")
    public Page<SalesResource> getSalesByYear(@PathVariable("year") String year, Pageable pageable) {
        return mapper.modelListToPage(salesService.getSaleByYear(Integer.parseInt(year)),pageable);
    }
    @ApiOperation(value = "Get a Sale by Month and Year",notes = "Esta consulta consiste en obtener una venta segun el año y el mes")
    @GetMapping("/sales/month/{month}/year/{year}")
    public Page<SalesResource> getSalesByMonthAndYear(@PathVariable("month") String month,@PathVariable("year") String year, Pageable pageable) {
        return mapper.modelListToPage(salesService.getSaleByMonthAndYear(Integer.parseInt(month),Integer.parseInt(year)),pageable);
    }
    @ApiOperation(value = "Get a Sale by User ID, Month and Year",notes = "Esta consulta consiste en obtener una venta segun el ID del usuario, el año y el mes")
    @GetMapping("/sales/user/{userId}/month/{month}/year/{year}")
    public Page<SalesResource> getSalesByUserIdAndMonthAndYear(@PathVariable("userId") Long userId,@PathVariable("month") String month,@PathVariable("year") String year, Pageable pageable) {
        return mapper.modelListToPage(salesService.getSaleByUserIdAndMonthAndYear(userId,Integer.parseInt(month),Integer.parseInt(year)),pageable);
    }
    @ApiOperation(value = "Get Number of Sale by Customer ID",notes = "Esta consulta consiste en obtener el numero de ventas segun el ID del cliente")
    @GetMapping("/numbersales/customer/{customerid}")
    public Long getNumberSalesByCustomerId(@PathVariable("customerid") Long customerid) {
        return salesService.getNumberofSaleByCustomerId(customerid);
    }
    @ApiOperation(value = "Get a Sale by Customer ID",notes = "Esta consulta consiste en obtener una venta segun el ID del cliente")
    @GetMapping("/sales/customer/{customerId}")
    public Page<SalesResource> getSalesByCustomerId(@PathVariable("customerId") Long customerId, Pageable pageable) {
        return mapper.modelListToPage(salesService.getSaleByCustomerId(customerId),pageable);
    }
    @ApiOperation(value = "Get a Sale by Status Id",notes = "Esta consulta consiste en obtener ventas segun su estado")
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
