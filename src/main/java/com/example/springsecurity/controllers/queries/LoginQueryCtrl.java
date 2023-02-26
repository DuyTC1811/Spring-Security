package com.example.springsecurity.controllers.queries;

import com.example.springsecurity.dto.requests.LoginRequest;
import com.example.springsecurity.dto.response.LoginResponse;
import io.cqrs.controller.QueryController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "API auth", description = "Auth management")
public class LoginQueryCtrl extends QueryController<LoginResponse, LoginRequest> {

    @Override
    @GetMapping("sign-in")
    protected ResponseEntity<LoginResponse> coordinator(LoginRequest loginRequest) {
        return execute(loginRequest);
    }
}
