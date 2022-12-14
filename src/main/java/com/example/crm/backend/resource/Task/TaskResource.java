package com.example.crm.backend.resource.Task;

import com.example.crm.backend.resource.Sales.SalesResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class TaskResource {

    private Long id;

    private String title;

    private Date date;

    private String inithour;

    private String finalhour;

    private String description;

    private SalesResource sales;

    private Date created_at;
}
