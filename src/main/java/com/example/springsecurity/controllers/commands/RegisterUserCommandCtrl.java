package com.example.springsecurity.controllers.commands;

import com.example.springsecurity.dto.requests.RegisterUserRequest;
import com.example.springsecurity.dto.response.RegisterUserResponse;
import io.cqrs.controller.CommandController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "API auth", description = "Auth management")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class RegisterUserCommandCtrl extends CommandController<RegisterUserResponse, RegisterUserRequest> {

    @Override
    @PostMapping("/sign-up")
    protected ResponseEntity<RegisterUserResponse> coordinator(@Valid @RequestBody RegisterUserRequest request) {
        return execute(request);
    }

}