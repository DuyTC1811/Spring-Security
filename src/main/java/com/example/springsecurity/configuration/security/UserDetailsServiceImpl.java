package com.example.springsecurity.configuration.security;

import com.example.springsecurity.models.UserInfo;
import com.example.springsecurity.mappers.IQueryUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IQueryUserMapper queryUserMapper;

    public UserDetailsServiceImpl(IQueryUserMapper queryUserMapper) {
        this.queryUserMapper = queryUserMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = queryUserMapper.findByUsername(username);
        if (Objects.isNull(userInfo)) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return UserDetailsImpl.build(userInfo);
    }
}
