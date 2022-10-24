package com.example.crm.backend.api;

import com.example.crm.backend.domain.activityAggregate.service.TaskService;
import com.example.crm.backend.domain.salesAggregate.service.SalesService;
import com.example.crm.backend.mapping.SalesMapper;
import com.example.crm.backend.mapping.TaskMapper;
import com.example.crm.backend.resource.Sales.CreateSalesResource;
import com.example.crm.backend.resource.Sales.SalesResource;
import com.example.crm.backend.resource.Sales.UpdateStatusSalesResource;
import com.example.crm.backend.resource.Task.CreateTaskResource;
import com.example.crm.backend.resource.Task.TaskResource;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper mapper;

    @ApiOperation(value = "Get all Tasks",notes = "Esta consulta consiste en obtener todas las actividades")
    @GetMapping("/tasks")
    public Page<TaskResource> getAllTasks(Pageable pageable) {
        return mapper.modelListToPage(taskService.getTasks(), pageable);
    }
    @ApiOperation(value = "Get a Task by ID",notes = "Esta consulta consiste en obtener una actividad segun su ID")
    @GetMapping("/tasks/{taskId}")
    public TaskResource getTaskById(@PathVariable("taskId") Long taskId) {
        return mapper.toResource(taskService.getTaskById(taskId));
    }
    @ApiOperation(value = "Get a Task by Sales Id",notes = "Esta consulta consiste en obtener una actividad segun el Id de las ventas")
    @GetMapping("/sales/{saleId}/tasks")
    public Page<TaskResource> getTaskBySalesId(@PathVariable("saleId") Long saleId,Pageable pageable) {
        return mapper.modelListToPage(taskService.getTasksBySalesId(saleId),pageable);
    }

    @ApiOperation(value = "Create a Task",notes = "Esta consulta consiste en crear una actividad")
    @PostMapping("/sales/{saleId}/tasks")
    public TaskResource createTask(@PathVariable("saleId") Long saleId, @RequestBody CreateTaskResource request) {
        return mapper.toResource(taskService.createTask(saleId,mapper.toModel(request)));
    }

    @ApiOperation(value = "Delete a Task",notes = "Esta consulta consiste en eliminar una actividad")
    @DeleteMapping("tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        return taskService.deleteTask(taskId);
    }
}
