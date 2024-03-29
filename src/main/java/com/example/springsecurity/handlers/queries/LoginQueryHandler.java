package com.example.springsecurity.handlers.queries;

import com.example.springsecurity.configuration.jwt.JwtUtils;
import com.example.springsecurity.dto.requests.LoginRequest;
import com.example.springsecurity.dto.response.LoginResponse;
import com.example.springsecurity.mappers.commands.ICommandUserMapper;
import com.example.springsecurity.mappers.queries.IQueryUserMapper;
import io.cqrs.model.BaseResponse;
import io.cqrs.query.IQueryHandler;
import io.exceptions.models.UserPasswordException;
import io.utilities.cache.IRedisValueOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginQueryHandler implements IQueryHandler<LoginResponse, LoginRequest> {
    private final JwtUtils jwtUtils;
    private final IQueryUserMapper queryUserMapper;
    private final ICommandUserMapper commandUserMapper;
    private final IRedisValueOperation<String> redisValueOperation;
    @Value("${spring.security.jwtRefreshExpirationMs}")
    private Integer refreshTokenDurationMs;

    public LoginQueryHandler(
            JwtUtils jwtUtils, IRedisValueOperation<String> redisValueOperation,
            IQueryUserMapper queryUserMapper, ICommandUserMapper commandUserMapper) {
        this.jwtUtils = jwtUtils;
        this.redisValueOperation = redisValueOperation;
        this.queryUserMapper = queryUserMapper;
        this.commandUserMapper = commandUserMapper;
    }

    @Override
    public BaseResponse<LoginResponse> handler(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        if (username == null || password == null) {
            throw new UserPasswordException(username, password);
        }
        String jwt = jwtUtils.generateJwtToken(username, password);

//      lastLogin
        Map<String, Object> param = new HashMap<>();
        param.put("time", Timestamp.valueOf(LocalDateTime.now()));
        param.put("username", username);
        commandUserMapper.lastLogin(param);

        return new BaseResponse<>(new LoginResponse(jwt));
    }
}
