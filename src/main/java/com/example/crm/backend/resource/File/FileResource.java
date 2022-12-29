package com.example.crm.backend.resource.File;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileResource {
    private Long id;

    private String filename;

    private Long userid;

    private Long saleid;
}
