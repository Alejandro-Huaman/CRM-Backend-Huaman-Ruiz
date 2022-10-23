package com.example.crm.backend.resource.User;

import com.example.crm.backend.domain.userAggregate.model.enumeration.RolName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRolUserResource {
    private RolName rolname;
}
