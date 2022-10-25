package com.example.crm.backend.security.service;

import com.example.crm.backend.domain.userAggregate.model.entity.Rol;
import com.example.crm.backend.domain.userAggregate.model.entity.User;
import com.example.crm.backend.domain.userAggregate.model.enumeration.RolName;
import com.example.crm.backend.domain.userAggregate.service.RolService;
import com.example.crm.backend.domain.userAggregate.service.UserService;
import com.example.crm.backend.security.dto.LoginUser;
import com.example.crm.backend.security.dto.newUser;
import com.example.crm.backend.security.jwt.jwtDto;
import com.example.crm.backend.security.jwt.jwtProvider;
import com.example.crm.shared.exception.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    jwtProvider jwtprovider;

    @Autowired
    RolService rolService;

    public Rol getroleSalesManager() throws Message {

        Optional<Rol> value= rolService.findByName(RolName.Sales_Manager);
        if(value.isPresent()){
            return value.get();
        }
        throw new Message("Error");
    }

    public Rol getroleProjectManager() throws Message {

        Optional<Rol> value= rolService.findByName(RolName.Project_Manager);
        if(value.isPresent()){
            return value.get();
        }
        throw new Message("Error");
    }

    public Rol getroleEngineeringChief() throws Message {

        Optional<Rol> value= rolService.findByName(RolName.Engineering_chief);
        if(value.isPresent()){
            return value.get();
        }
        throw new Message("Error");
    }

    public ResponseEntity<?> registerSalesManager(newUser request, BindingResult bindingResult) throws Message {
        if (bindingResult.hasErrors()){
            return new ResponseEntity(new Message("campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByNombreUsuario(request.getUsername())){
            return new ResponseEntity(new Message("ya existe nombre de usuario"), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(request.getEmail())){
            return new ResponseEntity(new Message("ya existe email"), HttpStatus.BAD_REQUEST);
        }
        User user= new User();
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(getroleSalesManager());
        user.setRoles(roles);
        user.setRolName(RolName.Sales_Manager);
        userService.createUser(user);
        return new ResponseEntity(new Message("new sales manager saved"),HttpStatus.CREATED);

    }

    public ResponseEntity<?> registerProjectManager(newUser request, BindingResult bindingResult) throws Message {
        if (bindingResult.hasErrors()){
            return new ResponseEntity(new Message("campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByNombreUsuario(request.getUsername())){
            return new ResponseEntity(new Message("ya existe nombre de usuario"), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(request.getEmail())){
            return new ResponseEntity(new Message("ya existe email"), HttpStatus.BAD_REQUEST);
        }
        User user= new User();
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(getroleProjectManager());
        user.setRoles(roles);
        user.setRolName(RolName.Project_Manager);
        userService.createUser(user);
        return new ResponseEntity(new Message("new project manager saved"),HttpStatus.CREATED);

    }

    public ResponseEntity<?> registerEngineeringChief(newUser request, BindingResult bindingResult) throws Message {
        if (bindingResult.hasErrors()){
            return new ResponseEntity(new Message("campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByNombreUsuario(request.getUsername())){
            return new ResponseEntity(new Message("ya existe nombre de usuario"), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(request.getEmail())){
            return new ResponseEntity(new Message("ya existe email"), HttpStatus.BAD_REQUEST);
        }
        User user= new User();
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(getroleEngineeringChief());
        user.setRoles(roles);
        user.setRolName(RolName.Engineering_chief);
        userService.createUser(user);
        return new ResponseEntity(new Message("new engineering chief saved"),HttpStatus.CREATED);

    }


    public ResponseEntity<jwtDto> login(LoginUser loginUser, BindingResult bindingResult){

        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtprovider.generateToken(authentication);
        jwtDto jwtDto = new jwtDto(jwt);
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
