package com.example.crm.backend.resource.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResource {
    private String name;

    private String lastname;

    private String email;

    private String username;

    private String password;

    private String typeusersale;
}
