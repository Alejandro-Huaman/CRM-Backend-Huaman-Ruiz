package com.example.crm.backend.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("CrmMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public CustomerMapper customerMapper() {
        return new CustomerMapper();
    }

    @Bean
    public SalesMapper salesMapper() {
        return new SalesMapper();
    }

    @Bean
    public TaskMapper taskMapper() {
        return new TaskMapper();
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public ImageMapper imageMapper() {
        return new ImageMapper();
    }

}
