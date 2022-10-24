package com.example.crm.backend.service;

import com.example.crm.backend.domain.activityAggregate.model.entity.Task;
import com.example.crm.backend.domain.activityAggregate.persistence.TaskRepository;
import com.example.crm.backend.domain.activityAggregate.service.TaskService;
import com.example.crm.backend.domain.salesAggregate.persistence.SalesRepository;
import com.example.crm.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private static final String ENTITY = "Task";
    private static final String ENTITY2 = "Sales";

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SalesRepository salesRepository;

    public TaskServiceImpl() {

    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Page<Task> getTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, taskId));
    }

    @Override
    public List<Task> getTasksBySalesId(Long saleId) {
        return taskRepository.findBysalesId(saleId);
    }

    @Override
    public Task createTask(Long saleId,Task task) {
        Date date = new Date();
        return salesRepository.findById(saleId)
                .map(sales -> {
                    task.setSales(sales);
                    task.setDate(date);
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY2, saleId));
    }

    @Override
    public ResponseEntity<?> deleteTask(Long taskId) {
        return taskRepository.findById(taskId).map(post -> {
            taskRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, taskId));
    }
}
