package com.example.crm.backend.domain.activityAggregate.model.entity;

import com.example.crm.backend.domain.salesAggregate.model.entity.Sales;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String title;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Size(max = 100)
    private String description;

    @ManyToOne(targetEntity = Sales.class)
    @JoinColumn(name = "salesid")
    private Sales sales;

}
