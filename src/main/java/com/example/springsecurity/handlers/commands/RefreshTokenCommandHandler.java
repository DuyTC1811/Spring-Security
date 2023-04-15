//package com.example.springsecurity.handlers.commands;
//
//import com.example.springsecurity.configuration.jwt.JwtUtils;
//import com.example.springsecurity.dto.requests.TokenRefreshRequest;
//import com.example.springsecurity.dto.response.TokenRefreshResponse;
//import com.example.springsecurity.models.UserInfo;
//import io.cqrs.command.ICommandHandler;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class RefreshTokenCommandHandler implements ICommandHandler<TokenRefreshResponse, TokenRefreshRequest> {
//    private final JwtUtils jwtUtils;
//
//    public RefreshTokenCommandHandler(JwtUtils jwtUtils) {
//        this.jwtUtils = jwtUtils;
//    }
//
//    @Override
//    public TokenRefreshResponse handler(TokenRefreshRequest request) {
//        String newToken = jwtUtils.generateTokenFromUsername(request.getUsername());
//        return new TokenRefreshResponse(newToken);
//    }
//}
