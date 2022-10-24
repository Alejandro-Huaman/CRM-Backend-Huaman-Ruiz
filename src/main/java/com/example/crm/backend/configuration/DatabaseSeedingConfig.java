package com.example.crm.backend.configuration;

import com.example.crm.backend.domain.salesAggregate.service.StatusService;
import com.example.crm.backend.domain.userAggregate.service.RolService;
import com.example.crm.shared.exception.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
@Component
public class DatabaseSeedingConfig {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseSeedingConfig.class);

    @Autowired
    RolService rolService;

    @Autowired
    StatusService statusService;


    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) throws Message {
        String name = event.getApplicationContext().getId();
        logger.info("Starting Database Seeding Process for {} at {}", name, new Timestamp(System.currentTimeMillis()));
        rolService.seed();
        statusService.seed();
        logger.info("Finished Database Seeding Process for {} at {}", name, new Timestamp(System.currentTimeMillis()));

    }
}
