package com.example.crm.backend.domain.salesAggregate.persistence;

import com.example.crm.backend.domain.salesAggregate.model.entity.Customer;
import com.example.crm.backend.domain.salesAggregate.model.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales,Long> {
    List<Sales> findByUserId(Long userId);
    List<Sales> findByCustomerId(Long customerId);
    List<Sales> findByMonth(Integer month);
    List<Sales> findByYear(Integer year);
    List<Sales> findByMonthAndYear(Integer month,Integer year);
    List<Sales> findByUserIdAndMonthAndYear(Long userId,Integer month,Integer year);
    @Query(value = "SELECT COUNT(*) FROM sales Where customerid=:customerid",nativeQuery = true)
    Long CountSaleByCustomerId(@Param("customerid") Long customerid);
}
