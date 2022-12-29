package com.example.crm.backend.mapping;

import com.example.crm.backend.domain.salesAggregate.model.entity.File;
import com.example.crm.backend.domain.userAggregate.model.entity.Image;
import com.example.crm.backend.resource.File.FileResource;
import com.example.crm.backend.resource.Image.ImageResource;
import com.example.crm.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class FileMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public FileResource toResource(File model) {
        return mapper.map(model, FileResource.class);
    }

    public Page<FileResource> modelListToPage(List<File> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, FileResource.class), pageable, modelList.size());
    }

}
