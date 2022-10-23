package com.example.crm.backend.domain.salesAggregate.model.entity;

import com.example.crm.backend.domain.userAggregate.model.entity.Rol;
import com.example.crm.backend.domain.userAggregate.model.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String name;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customerid")
    private Customer customer;

    @NotNull
    @NotBlank
    private Date finishdate;

    @NotNull
    @NotBlank
    private String amount;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sales_status",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "status_id"))
    private Set<Status> status = new HashSet<>();
}
