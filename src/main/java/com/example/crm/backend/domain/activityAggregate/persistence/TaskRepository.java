package com.example.crm.backend.domain.activityAggregate.persistence;

import com.example.crm.backend.domain.activityAggregate.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

}
