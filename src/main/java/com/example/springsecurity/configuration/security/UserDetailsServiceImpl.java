package com.example.springsecurity.configuration.security;

import com.example.springsecurity.mappers.queries.IQueryUserMapper;
import com.example.springsecurity.models.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IQueryUserMapper queryUserMapper;

    public UserDetailsServiceImpl(IQueryUserMapper queryUserMapper) {
        this.queryUserMapper = queryUserMapper;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = queryUserMapper.findByUsername(username);
        if (Objects.isNull(userInfo)) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        Set<String> roles = queryUserMapper.findByUserRoles(username);
        userInfo.setRoles(roles);
        return UserDetailsImpl.build(userInfo);
    }

}
