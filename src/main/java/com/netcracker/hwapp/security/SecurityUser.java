package com.netcracker.hwapp.security;

import com.netcracker.hwapp.enums.Status;
import com.netcracker.hwapp.model.Developer;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final Boolean isActive;

    public SecurityUser(String username, String password, List<SimpleGrantedAuthority> authorities, Boolean isActive) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
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
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUser(Developer developer) {
        return new User(
                developer.getEmail(),
                developer.getPassword(),
                developer.getStatus().equals(Status.ACTIVE),
                developer.getStatus().equals(Status.ACTIVE),
                developer.getStatus().equals(Status.ACTIVE),
                developer.getStatus().equals(Status.ACTIVE),
                developer.getRole().getAuthorities()
        );
    }
}
