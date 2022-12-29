package com.example.crm.backend.domain.salesAggregate.service;

import com.example.crm.backend.domain.salesAggregate.model.entity.File;
import com.example.crm.backend.domain.userAggregate.model.entity.Image;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.util.List;

public interface FileService {

    File uploadFileforUserId(Long userid,File file);
    File getFileByUserId(Long userid);
    ResponseEntity<?> deletefile(Long fileId);
}
