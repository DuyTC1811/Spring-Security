package com.example.springsecurity.controllers.commands;

import com.example.springsecurity.dto.requests.TokenRefreshRequest;
import com.example.springsecurity.dto.response.TokenRefreshResponse;
import io.cqrs.controller.CommandController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "API auth", description = "Auth management")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class RefreshTokenCommandCtrl extends CommandController<TokenRefreshResponse, TokenRefreshRequest> {

    @Override
    @PostMapping("/refresh-token")
    protected ResponseEntity<TokenRefreshResponse> coordinator(@RequestBody TokenRefreshRequest request) {
        return execute(request);
    }
}
