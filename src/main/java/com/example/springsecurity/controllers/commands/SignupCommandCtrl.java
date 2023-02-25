package com.example.springsecurity.controllers.commands;

import com.example.springsecurity.dto.requests.SignupRequest;
import com.example.springsecurity.dto.response.SignupResponse;
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
public class SignupCommandCtrl extends CommandController<SignupResponse, SignupRequest> {
    @Override
    @PostMapping("/sing-up")
    protected ResponseEntity<SignupResponse> coordinator(@Valid @RequestBody SignupRequest signupRequest) {
        return execute(signupRequest);
    }

}