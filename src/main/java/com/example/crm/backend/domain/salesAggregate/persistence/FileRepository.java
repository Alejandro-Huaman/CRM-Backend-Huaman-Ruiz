package com.example.crm.backend.domain.salesAggregate.persistence;

import com.example.crm.backend.domain.salesAggregate.model.entity.File;
import com.example.crm.backend.domain.userAggregate.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {

    File findByUserid(Long userid);

}
