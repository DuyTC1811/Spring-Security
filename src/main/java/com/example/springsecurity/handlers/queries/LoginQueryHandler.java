package com.example.springsecurity.handlers.queries;

import com.example.springsecurity.configuration.jwt.JwtUtils;
import com.example.springsecurity.configuration.security.UserDetailsImpl;
import com.example.springsecurity.dto.requests.LoginRequest;
import com.example.springsecurity.dto.response.LoginResponse;
import io.cqrs.query.IQueryHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginQueryHandler implements IQueryHandler<LoginResponse, LoginRequest> {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public LoginQueryHandler(JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public LoginResponse handler(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new LoginResponse(jwt, userDetails.getUuid(), userDetails.getUsername(), userDetails.getEmail(), roles);
    }
}
