package com.example.nitstudenthubsb.service;

import com.example.nitstudenthubsb.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;
    private final Long id;
    private final String password;
    private final String username;
private final Collection<? extends GrantedAuthority> authorities;

public UserDetailsImpl(Long id, String password, String username,Collection<? extends GrantedAuthority> authorities) {
    this.id=id;
    this.username = username;
    this.password = password;
    this.authorities=authorities;

    }

    public static UserDetailsImpl build(User user) {
    GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
    return new UserDetailsImpl(user.getId(), user.getPassword(), user.getRollNo().toLowerCase(), List.of(authority));
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
}
