package com.example.springsecurity.controllers.commands;

import com.example.springsecurity.dto.requests.RegisterUserRequest;
import com.example.springsecurity.dto.response.RegisterResponse;
import io.cqrs.controller.CommandController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "API auth", description = "Auth management")
public class SignupCommandCtrl extends CommandController<RegisterResponse, RegisterUserRequest> {
    @Override
    @PostMapping("/sing-up")
    protected ResponseEntity<RegisterResponse> coordinator(@Valid @RequestBody RegisterUserRequest request) {
        return execute(request);
    }

}