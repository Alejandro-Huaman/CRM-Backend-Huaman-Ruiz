package com.example.crm.backend.domain.userAggregate.persistence;

import com.example.crm.backend.domain.activityAggregate.model.entity.Task;
import com.example.crm.backend.domain.userAggregate.model.entity.Rol;
import com.example.crm.backend.domain.userAggregate.model.enumeration.RolName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {
    Optional<Rol> findByrolname(RolName name);

    boolean existsByrolname(RolName name);
}
