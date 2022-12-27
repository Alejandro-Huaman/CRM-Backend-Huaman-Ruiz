package com.example.crm.backend.mapping;

import com.example.crm.backend.domain.activityAggregate.model.entity.Task;
import com.example.crm.backend.domain.salesAggregate.model.entity.Customer;
import com.example.crm.backend.domain.userAggregate.model.entity.User;
import com.example.crm.backend.resource.Customer.UpdateCustomerResource;
import com.example.crm.backend.resource.Task.CreateTaskResource;
import com.example.crm.backend.resource.Task.TaskResource;
import com.example.crm.backend.resource.User.*;
import com.example.crm.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class UserMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public UserResource toResource(User model) {
        return mapper.map(model, UserResource.class);
    }

    public Page<UserResource> modelListToPage(List<User> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, UserResource.class), pageable, modelList.size());
    }
    public User toModel(CreateUserResource resource) {
        return mapper.map(resource, User.class);
    }

    public User toModel(UpdateUserResource resource) {
        return mapper.map(resource, User.class);
    }

    public User toModel(UpdateRolUserResource resource) {
        return mapper.map(resource, User.class);
    }
    public User toModel(UpdateUserPasswordResource resource) {
        return mapper.map(resource, User.class);
    }
}
