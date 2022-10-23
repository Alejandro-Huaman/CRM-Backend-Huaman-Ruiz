package com.example.crm.backend.domain.activityAggregate.service;

import com.example.crm.backend.domain.activityAggregate.model.entity.Task;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {

    List<Task> getTasks();
    Page<Task> getTasks(Pageable pageable);
    Task getTaskBySalesId(Long saleId);
    void createTask(Task task);
    ResponseEntity<?> deleteTask(Long taskId);

}
