package com.example.crm.backend.domain.salesAggregate.model.entity;

import com.example.crm.backend.domain.activityAggregate.model.entity.Task;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 30)
    @Column(unique = true)
    private String businessname;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String ruc;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String fiscaladdress;

    @NotNull
    @NotBlank
    @Email
    @Size(max = 70)
    @Column(unique = true)
    private String email;

    @OneToMany(targetEntity = Sales.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "customerid",referencedColumnName = "id")
    private List<Sales> sales;
}
