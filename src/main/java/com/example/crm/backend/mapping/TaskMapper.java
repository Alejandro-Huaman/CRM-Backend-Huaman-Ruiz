package com.example.crm.backend.mapping;

import com.example.crm.backend.domain.activityAggregate.model.entity.Task;
import com.example.crm.backend.domain.salesAggregate.model.entity.Sales;
import com.example.crm.backend.resource.Sales.CreateSalesResource;
import com.example.crm.backend.resource.Sales.SalesResource;
import com.example.crm.backend.resource.Task.CreateTaskResource;
import com.example.crm.backend.resource.Task.TaskResource;
import com.example.crm.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class TaskMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public TaskResource toResource(Task model) {
        return mapper.map(model, TaskResource.class);
    }

    public Page<TaskResource> modelListToPage(List<Task> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, TaskResource.class), pageable, modelList.size());
    }
    public Task toModel(CreateTaskResource resource) {
        return mapper.map(resource, Task.class);
    }
}
