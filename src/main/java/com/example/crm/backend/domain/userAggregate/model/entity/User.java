package com.example.crm.backend.domain.userAggregate.model.entity;

import com.example.crm.backend.domain.salesAggregate.model.entity.Sales;
import com.example.crm.backend.domain.userAggregate.model.enumeration.RolName;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

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

    private String username;

    @NotNull
    @NotBlank
    private String password;

    private String typeusersale;

    @Enumerated(EnumType.STRING)
    private RolName rolName;

    @OneToMany(targetEntity = Sales.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "userid",referencedColumnName = "id")
    private List<Sales> sales;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Rol> roles=new HashSet<>();

}
