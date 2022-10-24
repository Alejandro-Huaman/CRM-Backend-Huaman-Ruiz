package com.example.crm.backend.service;

import com.example.crm.backend.domain.activityAggregate.service.TaskService;
import com.example.crm.backend.domain.salesAggregate.persistence.SalesRepository;
import com.example.crm.backend.domain.userAggregate.model.entity.Rol;
import com.example.crm.backend.domain.userAggregate.model.entity.User;
import com.example.crm.backend.domain.userAggregate.model.enumeration.RolName;
import com.example.crm.backend.domain.userAggregate.persistence.UserRepository;
import com.example.crm.backend.domain.userAggregate.service.RolService;
import com.example.crm.backend.domain.userAggregate.service.UserService;
import com.example.crm.backend.resource.User.UpdateRolUserResource;
import com.example.crm.backend.resource.User.UpdateUserResource;
import com.example.crm.shared.exception.ResourceNotFoundException;
import com.example.crm.shared.exception.ResourcePerzonalized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final String ENTITY = "User";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RolService rolService;

    public UserServiceImpl() {

    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public User updateRolUser(Long userId, User request) {
        return userRepository.findById(userId).map(post->{
            Set<Rol> roles = new HashSet<>();
            roles.add(rolService.findByName(request.getRolName()).get());
            post.setRoles(roles);
            post.setRolName(request.getRolName());
            userRepository.save(post);
            return post;
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public User createUser(User user) {
        if(userRepository.existsByEmail(user.getEmail()))
            throw  new ResourcePerzonalized("Ya existe este email");
        if(userRepository.existsByUsername(user.getUsername()))
            throw  new ResourcePerzonalized("Ya existe este nombre de usuario");

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User request) {
        return userRepository.findById(userId).map(post->{
            post.setName(request.getName());
            post.setLastname(request.getLastname());
            post.setEmail(request.getEmail());
            post.setUsername(request.getUsername());
            post.setPhone(request.getPhone());
            post.setPassword(request.getPassword());
            userRepository.save(post);
            return post;
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        return userRepository.findById(userId).map(post -> {
            userRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }
}
