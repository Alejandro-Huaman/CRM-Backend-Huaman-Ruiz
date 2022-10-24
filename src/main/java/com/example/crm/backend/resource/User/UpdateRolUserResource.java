package com.example.crm.backend.resource.User;

import com.example.crm.backend.domain.userAggregate.model.enumeration.RolName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class UpdateRolUserResource {
    @Enumerated(EnumType.STRING)
    private RolName rolname;
}
