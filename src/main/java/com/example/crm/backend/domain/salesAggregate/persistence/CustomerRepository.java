package com.example.crm.backend.domain.salesAggregate.persistence;

import com.example.crm.backend.domain.activityAggregate.model.entity.Task;
import com.example.crm.backend.domain.salesAggregate.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findByName(String name);
    Boolean existsByEmail(String email);
}
