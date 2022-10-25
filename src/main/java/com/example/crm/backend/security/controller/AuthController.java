package com.example.crm.backend.security.controller;

import com.example.crm.backend.security.dto.LoginUser;
import com.example.crm.backend.security.dto.newUser;
import com.example.crm.backend.security.jwt.jwtDto;
import com.example.crm.backend.security.service.AuthService;
import com.example.crm.shared.exception.Message;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @ApiOperation(value="Register a Project Manager",notes = "Esta consulta nos ayuda a registrar a un gestionador de proyectos")
    @PostMapping("/projectmanager")
    public ResponseEntity<?> registerProjectManager(@Valid @RequestBody newUser request, BindingResult bindingResult) throws Message {
        return authService.registerProjectManager(request,bindingResult);
    }

    @ApiOperation(value="Register a Sales Manager",notes = "Esta consulta nos ayuda a registrar a un gestionador de ventas")
    @PostMapping("/salesmanager")
    public ResponseEntity<?> registerSalesManager(@Valid @RequestBody newUser request, BindingResult bindingResult) throws Message {
        return authService.registerSalesManager(request,bindingResult);
    }

    @ApiOperation(value="Register a Engineering Chief",notes = "Esta consulta nos ayuda a registrar a un jefe de ingenieros")
    @PostMapping("/engineerchief")
    public ResponseEntity<?> registerEngineeringChief(@Valid @RequestBody newUser request, BindingResult bindingResult) throws Message {
        return authService.registerEngineeringChief(request,bindingResult);
    }

    @ApiOperation(value="Login",notes = "Esta consulta nos ayuda a logear a un usuario ya registrado")
    @PostMapping("/login")
    public ResponseEntity<jwtDto>login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
        return authService.login(loginUser,bindingResult);
    }
}
