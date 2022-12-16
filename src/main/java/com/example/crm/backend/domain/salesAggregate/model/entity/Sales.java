package com.example.crm.backend.domain.salesAggregate.model.entity;

import com.example.crm.backend.domain.activityAggregate.model.entity.Task;
import com.example.crm.backend.domain.salesAggregate.model.enumeration.StatusName;
import com.example.crm.backend.domain.userAggregate.model.entity.Rol;
import com.example.crm.backend.domain.userAggregate.model.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
    private Date finishdate;

    @NotNull
    private Integer month;

    @NotNull
    private Integer year;

    @NotNull
    @NotBlank
    private String amount;

    private Date created_at;

    @Enumerated(EnumType.STRING)
    private StatusName statusName;

    @OneToMany(targetEntity = Task.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "salesid",referencedColumnName = "id")
    private List<Task> tasks;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sales_status",
            joinColumns = @JoinColumn(name = "sales_id"),
            inverseJoinColumns = @JoinColumn(name = "status_id"))
    private Set<Status> status=new HashSet<>();

}
