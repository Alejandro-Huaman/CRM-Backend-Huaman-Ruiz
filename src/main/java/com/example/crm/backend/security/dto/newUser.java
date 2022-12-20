package com.example.crm.backend.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class newUser {

    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @NotBlank
    private String email;

    private String username;

    @NotBlank
    private String password;

    private String typeusersale;

}
