package com.example.crm.backend.api;

import com.example.crm.backend.domain.activityAggregate.service.TaskService;
import com.example.crm.backend.domain.salesAggregate.service.CustomerService;
import com.example.crm.backend.domain.userAggregate.service.UserService;
import com.example.crm.backend.mapping.CustomerMapper;
import com.example.crm.backend.mapping.TaskMapper;
import com.example.crm.backend.mapping.UserMapper;
import com.example.crm.backend.resource.Customer.CreateCustomerResource;
import com.example.crm.backend.resource.Customer.CustomerResource;
import com.example.crm.backend.resource.Customer.UpdateCustomerResource;
import com.example.crm.backend.resource.Task.CreateTaskResource;
import com.example.crm.backend.resource.Task.TaskResource;
import com.example.crm.backend.resource.User.CreateUserResource;
import com.example.crm.backend.resource.User.UpdateRolUserResource;
import com.example.crm.backend.resource.User.UpdateUserResource;
import com.example.crm.backend.resource.User.UserResource;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper mapper;

    @ApiOperation(value = "Get all Users",notes = "Esta consulta consiste en obtener a todos los usuarios")
    @GetMapping
    public Page<UserResource> getAllUsers(Pageable pageable) {
        return mapper.modelListToPage(userService.getUsers(), pageable);
    }
    @ApiOperation(value = "Get a User by ID",notes = "Esta consulta consiste en obtener un usuario segun su ID")
    @GetMapping("{userId}")
    public UserResource getUserById(@PathVariable("userId") Long userId) {
        return mapper.toResource(userService.getUserById(userId));
    }

    @ApiOperation(value = "Get a User by Email",notes = "Esta consulta consiste en obtener un usuario segun su email")
    @GetMapping("email/{email}")
    public UserResource getUserByUsername(@PathVariable("email") String email) {
        return mapper.toResource(userService.getUserByEmail(email));
    }

    @ApiOperation(value = "Create a User",notes = "Esta consulta consiste en crear a un usuario")
    @PostMapping
    public UserResource createUser(@RequestBody CreateUserResource request) {
        return mapper.toResource(userService.createUser(mapper.toModel(request)));
    }

    @ApiOperation(value = "Update Rol of a User",notes = "Esta consulta consiste en actualizar el rol de un usuario")
    @PutMapping("{userId}/rol")
    public UserResource updateRolUser(@PathVariable Long userId, @RequestBody UpdateRolUserResource request) {
        return mapper.toResource(userService.updateRolUser(userId, mapper.toModel(request)));
    }

    @ApiOperation(value = "Update a User",notes = "Esta consulta consiste en actualizar la informacion de un usuario")
    @PutMapping("{userId}")
    public UserResource updateUser(@PathVariable Long userId, @RequestBody UpdateUserResource request) {
        return mapper.toResource(userService.updateUser(userId, mapper.toModel(request)));
    }

    @ApiOperation(value = "Delete a User",notes = "Esta consulta consiste en eliminar un usuario")
    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }
}
