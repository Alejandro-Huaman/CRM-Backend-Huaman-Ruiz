package com.example.crm.backend.domain.userAggregate.service;

import com.example.crm.backend.domain.userAggregate.model.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ImageService {
    List<Image> getAll();
    Page<Image> getAll(Pageable pageable);
    Image getById(Long ImageId);
    Image createforuser(Long userId,Image image);
    List<Image> getImageByUserId(Long userId);
    ResponseEntity<?> delete(Long ImageId);
    boolean exists(Long id);
}
