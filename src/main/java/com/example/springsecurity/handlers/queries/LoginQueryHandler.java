//package com.example.springsecurity.handlers.queries;
//
//import com.example.springsecurity.configuration.jwt.JwtUtils;
//import com.example.springsecurity.configuration.security.UserDetailsImpl;
//import com.example.springsecurity.dto.requests.LoginRequest;
//import com.example.springsecurity.dto.response.LoginResponse;
//import io.cqrs.query.IQueryHandler;
//import io.exceptions.models.UserPasswordException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LoginQueryHandler implements IQueryHandler<LoginResponse, LoginRequest> {
//    private final JwtUtils jwtUtils;
//    private final AuthenticationManager authenticationManager;
//
//    public LoginQueryHandler(JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
//        this.jwtUtils = jwtUtils;
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    public LoginResponse handler(LoginRequest loginRequest) {
//        String username = loginRequest.getUsername();
//        String password = loginRequest.getPassword();
//        if (username == null || password == null) {
//            throw new UserPasswordException(username, password);
//        }
//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
//        String jwt = jwtUtils.generateJwtToken(userPrincipal);
//        return new LoginResponse(jwt);
//    }
//}
