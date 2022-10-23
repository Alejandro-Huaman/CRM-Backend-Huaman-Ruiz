package com.example.crm.backend.service;

import com.example.crm.backend.domain.activityAggregate.model.entity.Task;
import com.example.crm.backend.domain.activityAggregate.persistence.TaskRepository;
import com.example.crm.backend.domain.activityAggregate.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private static final String ENTITY = "Task";

    @Autowired
    private TaskRepository taskRepository;

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
    public Task getTaskBySalesId(Long saleId) {
        return null;
    }

    @Override
    public void createTask(Task task) {

    }

    @Override
    public ResponseEntity<?> deleteTask(Long taskId) {
        return null;
    }
}
