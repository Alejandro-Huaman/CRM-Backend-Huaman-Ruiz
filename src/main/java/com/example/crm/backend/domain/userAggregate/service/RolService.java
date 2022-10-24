package com.example.crm.backend.domain.userAggregate.service;
import com.example.crm.backend.domain.userAggregate.model.entity.Rol;
import com.example.crm.backend.domain.userAggregate.model.enumeration.RolName;

import java.util.List;
import java.util.Optional;

public interface RolService {

    List<Rol> getRol();
    Optional<Rol> findByName(RolName name);
    void seed();

}
