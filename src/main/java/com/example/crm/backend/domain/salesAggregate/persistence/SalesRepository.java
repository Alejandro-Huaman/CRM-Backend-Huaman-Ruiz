package com.example.crm.backend.domain.salesAggregate.persistence;

import com.example.crm.backend.domain.salesAggregate.model.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales,Long> {

}
