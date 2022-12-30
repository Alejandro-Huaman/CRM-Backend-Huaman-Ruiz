package com.example.crm.backend.service;

import com.example.crm.backend.domain.salesAggregate.model.entity.Customer;
import com.example.crm.backend.domain.salesAggregate.model.entity.File;
import com.example.crm.backend.domain.salesAggregate.model.entity.Sales;
import com.example.crm.backend.domain.salesAggregate.persistence.FileRepository;
import com.example.crm.backend.domain.salesAggregate.persistence.SalesRepository;
import com.example.crm.backend.domain.salesAggregate.service.FileService;
import com.example.crm.backend.domain.userAggregate.persistence.UserRepository;
import com.example.crm.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private static final String ENTITY = "File";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SalesRepository saleRepository;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public File uploadFileforUserIdandSaleId(Long userid,Long saleid, File file) {
        Sales sale = saleRepository.findById(saleid)
                .orElseThrow(() -> new ResourceNotFoundException("Sale", saleid));

        return userRepository.findById(userid).map(user -> {
           file.setUserid(user.getId());
           file.setSaleid(sale.getId());
           return fileRepository.save(file);
        }).orElseThrow(() -> new ResourceNotFoundException("User", userid));
    }

    @Override
    public List<File> getFileByUserIdandSaleId(Long userid, Long saleid) {
        return fileRepository.findByUseridAndSaleid(userid,saleid);
    }

    @Override
    public ResponseEntity<?> deletefile(Long fileId) {
        return fileRepository.findById(fileId).map(file -> {
            fileRepository.delete(file);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, fileId));
    }
}
