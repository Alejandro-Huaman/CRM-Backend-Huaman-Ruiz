package com.example.crm.backend.domain.salesAggregate.service;

import com.example.crm.backend.domain.salesAggregate.model.entity.File;
import com.example.crm.backend.domain.userAggregate.model.entity.Image;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.util.List;

public interface FileService {

    File uploadFileforUserIdandSaleId(Long userid,Long saleid,File file);
    File getFileByUserIdandSaleId(Long userid,Long saleid);
    ResponseEntity<?> deletefile(Long fileId);
}
