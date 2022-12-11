package com.example.crm.backend.domain.salesAggregate.service;
import com.example.crm.backend.domain.salesAggregate.model.entity.Sales;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface SalesService {

    List<Sales> getSales();
    Page<Sales> getSales(Pageable pageable);
    Sales getSaleById(Long saleId);
    List<Sales> getSaleByStatus(Long statusId);
    Sales createSale(Long userId,Long customerId,Sales sale);
    Sales updateStatusSales(Long saleId,Sales request);
    ResponseEntity<?> deleteSale(Long saleId);
    List<Sales> getSaleByUserId(Long userId);
    List<Sales> getSaleByCustomerId(Long customerId);
    List<Sales> getSaleByMonth(Integer month);
    List<Sales> getSaleByYear(Integer year);
    Long getNumberofSaleByCustomerId(Long customerid);
    List<Sales> getSaleByMonthAndYear(Integer month,Integer year);
}
