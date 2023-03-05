package com.example.springsecurity.configuration.security;

import com.example.springsecurity.models.UserInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class UserDetailsImpl implements UserDetails {
    private String uuid;

    private String username;
    private String userCode;

    private String email;

    private String mobile;

    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(
            String uuid, String username, String userCode,
            String email, String mobile, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.uuid = uuid;
        this.username = username;
        this.userCode = userCode;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(UserInfo user) {
        List<GrantedAuthority> authorities = user.getRoles()
                .stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getUserId(),
                user.getUsername(),
                user.getUserCode(),
                user.getEmail(),
                user.getMobile(),
                user.getPassword(),
                authorities);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(uuid, user.uuid);
    }
}
