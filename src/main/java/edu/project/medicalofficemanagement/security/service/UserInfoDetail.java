package edu.project.medicalofficemanagement.security.service;

import edu.project.medicalofficemanagement.model.User;
import edu.project.medicalofficemanagement.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserInfoDetail implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final String email;
    private final Set<GrantedAuthority> authorities;

    public UserInfoDetail(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();

        // Convertir roles a GrantedAuthority, asumiendo que User tiene Set<Role> roles
        this.authorities = user.getRoles().stream()
                .map(Role::getName) // si Role tiene método getName() que devuelve String
                .map(roleName -> new SimpleGrantedAuthority("ROLE_" + roleName))
                .collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
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
        return true; // O mapear si tienes campo en User
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // O mapear si tienes campo en User
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // O mapear si tienes campo en User
    }

    @Override
    public boolean isEnabled() {
        return true; // O mapear si tienes campo en User
    }
}
