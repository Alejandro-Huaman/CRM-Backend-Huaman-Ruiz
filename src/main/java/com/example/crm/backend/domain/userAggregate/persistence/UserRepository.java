package com.example.crm.backend.domain.userAggregate.persistence;

import com.example.crm.backend.domain.activityAggregate.model.entity.Task;
import com.example.crm.backend.domain.userAggregate.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
