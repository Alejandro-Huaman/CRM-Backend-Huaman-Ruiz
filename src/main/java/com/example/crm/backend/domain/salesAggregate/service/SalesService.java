package com.example.crm.backend.domain.salesAggregate.service;
import com.example.crm.backend.domain.salesAggregate.model.entity.Customer;
import com.example.crm.backend.domain.salesAggregate.model.entity.Sales;
import com.example.crm.backend.domain.salesAggregate.model.entity.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SalesService {

    List<Sales> getSales();
    Page<Sales> getSales(Pageable pageable);
    Sales getSaleById(Long saleId);
    Sales getSaleByStatus(Status status);
    void createSale(Sales sale);
    Sales updateStatusSales(Long saleId,Sales request);
    ResponseEntity<?> deleteSale(Long saleId);

}
