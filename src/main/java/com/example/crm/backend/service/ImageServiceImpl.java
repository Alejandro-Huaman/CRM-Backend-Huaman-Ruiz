package com.example.crm.backend.service;

import com.example.crm.backend.domain.userAggregate.model.entity.Image;
import com.example.crm.backend.domain.userAggregate.persistence.ImageRepository;
import com.example.crm.backend.domain.userAggregate.service.ImageService;
import com.example.crm.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private static final String ENTITY = "Image";

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    @Override
    public Page<Image> getAll(Pageable pageable) {
        return imageRepository.findAll(pageable);
    }

    @Override
    public Image getById(Long ImageId) {
        return imageRepository.findById(ImageId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, ImageId));
    }

    @Override
    public Image createforuser(Long userId, Image image) {
        return null;
    }

    @Override
    public List<Image> getImageByUserId(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long ImageId) {
        return null;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }
}
