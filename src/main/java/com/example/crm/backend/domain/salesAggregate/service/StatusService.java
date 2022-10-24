package com.example.crm.backend.domain.salesAggregate.service;

import com.example.crm.backend.domain.salesAggregate.model.entity.Status;
import com.example.crm.backend.domain.salesAggregate.model.enumeration.StatusName;
import com.example.crm.backend.domain.userAggregate.model.entity.Rol;
import com.example.crm.backend.domain.userAggregate.model.enumeration.RolName;

import java.util.List;
import java.util.Optional;

public interface StatusService {
    List<Status> getStatus();
    Optional<Status> findByName(StatusName name);
    void seed();
}
