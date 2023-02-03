package com.example.springsecurity.controllers.commands;

import com.example.springsecurity.dto.requests.SignupRequest;
import com.example.springsecurity.dto.response.SignupResponse;
import com.example.springsecurity.services.SignupCommandSV;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "API auth", description = "Auth management")
public class SignupCommandCtrl {
    private final SignupCommandSV signupCommand;

    public SignupCommandCtrl(SignupCommandSV signupCommand) {
        this.signupCommand = signupCommand;
    }

    @PostMapping("/sing-up")
    public ResponseEntity<SignupResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        SignupResponse signupResponse = signupCommand.registerUser(signUpRequest);
        return new ResponseEntity<>(signupResponse, HttpStatus.OK);
    }
}