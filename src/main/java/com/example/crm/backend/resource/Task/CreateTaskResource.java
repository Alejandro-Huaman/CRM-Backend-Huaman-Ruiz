package com.example.crm.backend.resource.Task;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateTaskResource {

    private String title;

    private Date date;

    private String description;
}
