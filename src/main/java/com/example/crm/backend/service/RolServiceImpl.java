package com.example.crm.backend.service;

import com.example.crm.backend.domain.salesAggregate.service.SalesService;
import com.example.crm.backend.domain.userAggregate.model.entity.Rol;
import com.example.crm.backend.domain.userAggregate.model.enumeration.RolName;
import com.example.crm.backend.domain.userAggregate.persistence.RolRepository;
import com.example.crm.backend.domain.userAggregate.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    RolRepository rolRepository;

    private static String[] DEFAULT_ROLES = {"Sales_Manager","Project_Manager","Engineering_chief"};

    public RolServiceImpl(){

    }

    @Override
    public Optional<Rol> findByName(RolName name) {
        return rolRepository.findByrolname(name);
    }

    @Override
    public void seed() {
        Arrays.stream(DEFAULT_ROLES).forEach(name -> {
            RolName roleName = RolName.valueOf(name);
            if(!rolRepository.existsByrolname(roleName)) {
                rolRepository.save((new Rol()).withRolname(roleName));
            }
        } );
    }

    @Override
    public List<Rol> getRol() {
        return rolRepository.findAll();
    }
}
