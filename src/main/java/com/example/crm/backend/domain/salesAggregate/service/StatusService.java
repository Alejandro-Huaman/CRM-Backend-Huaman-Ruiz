package com.example.crm.backend.domain.salesAggregate.service;
import com.example.crm.backend.domain.salesAggregate.model.entity.Sales;
import com.example.crm.backend.domain.salesAggregate.model.entity.Status;
import com.example.crm.backend.domain.salesAggregate.model.enumeration.StatusName;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface StatusService {

    List<Status> getStatus();
    Optional<Status> findByName(StatusName name);

}
