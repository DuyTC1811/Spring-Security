package com.example.springsecurity.configuration.security;

import com.example.springsecurity.mappers.queries.IQueryUserMapper;
import com.example.springsecurity.models.UserInfo;
import io.utilities.cache.IRedisValueOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;

import static io.utilities.converter.ConverterStringUntil.converterToString;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IQueryUserMapper queryUserMapper;
    private final IRedisValueOperation<String> redisValueOperation;
    @Value("${spring.security.jwtRefreshExpirationMs}")
    private Integer refreshTokenDurationMs;

    public UserDetailsServiceImpl(
            IQueryUserMapper queryUserMapper,
            IRedisValueOperation<String> redisValueOperation) {
        this.queryUserMapper = queryUserMapper;
        this.redisValueOperation = redisValueOperation;
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
        UserDetailsImpl build = UserDetailsImpl.build(userInfo);
        // Khi kiểm tra thông tin người dùng sau đó lưu thông tin người dùng vào cache
        redisValueOperation.pushCache(username, refreshTokenDurationMs, converterToString(userInfo));
        return build;
    }
}
