package com.example.crm.backend.resource.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserResource {
    private String name;

    private String lastname;

    private String email;

    private String username;
}
