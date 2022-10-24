package com.example.crm.backend.service;

import com.example.crm.backend.domain.salesAggregate.model.entity.Status;
import com.example.crm.backend.domain.salesAggregate.model.enumeration.StatusName;
import com.example.crm.backend.domain.salesAggregate.persistence.StatusRepository;
import com.example.crm.backend.domain.salesAggregate.service.StatusService;
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
public class StatusServiceImpl implements StatusService {

    @Autowired
    StatusRepository statusRepository;

    private static String[] DEFAULT_STATUS = {"Qualification","Need_Analysis","Proposal","Negotiation","Closed_Won"};

    public StatusServiceImpl(){

    }

    @Override
    public Optional<Status> findByName(StatusName name) {
        return statusRepository.findByStatusname(name);
    }

    @Override
    public void seed() {
        Arrays.stream(DEFAULT_STATUS).forEach(name -> {
            StatusName statusName = StatusName.valueOf(name);
            if(!statusRepository.existsByStatusname(statusName)) {
                statusRepository.save((new Status()).withStatusname(statusName));
            }
        } );
    }

    @Override
    public List<Status> getStatus() {
        return statusRepository.findAll();
    }
}
