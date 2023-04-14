package com.example.springsecurity.configuration.security;

import com.example.springsecurity.models.UserInfo;
import com.example.springsecurity.repositorys.IUserInfoRepository;
import io.utilities.cache.IRedisValueOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.utilities.converter.ConverterStringUntil.converterToString;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IUserInfoRepository userInfoRepository;
    private final IRedisValueOperation<String> redisValueOperation;
    @Value("${spring.security.jwtRefreshExpirationMs}")
    private Integer refreshTokenDurationMs;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        UserInfo userInfo = userInfoRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: => " + username));
        UserDetailsImpl build = UserDetailsImpl.build(userInfo);
        // Khi kiểm tra thông tin người dùng sau đó lưu thông tin người dùng vào cache
        redisValueOperation.pushCache(username, refreshTokenDurationMs, converterToString(userInfo));
        return build;
    }
}
