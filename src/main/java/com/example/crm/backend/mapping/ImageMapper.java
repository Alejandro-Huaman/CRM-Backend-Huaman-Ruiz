package com.example.crm.backend.mapping;

import com.example.crm.backend.domain.salesAggregate.model.entity.Customer;
import com.example.crm.backend.domain.userAggregate.model.entity.Image;
import com.example.crm.backend.resource.Customer.CreateCustomerResource;
import com.example.crm.backend.resource.Customer.CustomerResource;
import com.example.crm.backend.resource.Customer.UpdateCustomerResource;
import com.example.crm.backend.resource.Image.ImageResource;
import com.example.crm.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ImageMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public ImageResource toResource(Image model) {
        return mapper.map(model, ImageResource.class);
    }

    public Page<ImageResource> modelListToPage(List<Image> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ImageResource.class), pageable, modelList.size());
    }

}
