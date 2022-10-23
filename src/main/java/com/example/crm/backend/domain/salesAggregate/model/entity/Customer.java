package com.example.crm.backend.domain.salesAggregate.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String lastname;

    @NotNull
    @NotBlank
    @Email
    @Size(max = 70)
    @Column(unique = true)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 15)
    private String phone;

    @NotNull
    @NotBlank
    @Size(max = 15)
    private String rut;
}
