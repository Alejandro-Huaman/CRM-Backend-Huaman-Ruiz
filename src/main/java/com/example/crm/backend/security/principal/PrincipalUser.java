package com.example.crm.backend.security.principal;

import com.example.crm.backend.domain.userAggregate.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class PrincipalUser implements UserDetails {

    User usuario;

    private String name;

    private String lastname;

    private String email;

    private String phone;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public PrincipalUser(String name, String lastname, String email, String username, String password, String phone, Collection<? extends GrantedAuthority> authorities){
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static PrincipalUser build(User persona){
        List<GrantedAuthority> authorities=
                persona.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                        .getRolname().name())).collect(Collectors.toList());

        return new PrincipalUser(persona.getName(), persona.getLastname(), persona.getEmail(),persona.getUsername(), persona.getPassword(),persona.getPhone(),authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
