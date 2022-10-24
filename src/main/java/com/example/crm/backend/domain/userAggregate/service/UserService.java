package com.example.crm.backend.domain.userAggregate.service;
import com.example.crm.backend.domain.salesAggregate.model.entity.Customer;
import com.example.crm.backend.domain.userAggregate.model.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<User> getUsers();
    Page<User> getUsers(Pageable pageable);
    User getUserById(Long userId);
    User updateRolUser(Long userId, User request);
    User createUser(User user);
    User updateUser(Long userId, User request);
    ResponseEntity<?> deleteUser(Long userId);
}
