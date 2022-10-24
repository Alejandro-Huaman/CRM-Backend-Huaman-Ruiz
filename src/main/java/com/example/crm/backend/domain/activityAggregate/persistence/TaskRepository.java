package com.example.crm.backend.domain.activityAggregate.persistence;

import com.example.crm.backend.domain.activityAggregate.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findBysalesId(Long salesid);
}
