//package com.example.springsecurity.controllers.queries;
//
//import com.example.springsecurity.dto.requests.LoginRequest;
//import com.example.springsecurity.dto.response.LoginResponse;
//import io.cqrs.controller.QueryController;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//@Tag(name = "API auth", description = "Auth management")
//@CrossOrigin(origins = "*", maxAge = 3600)
//public class LoginQueryCtrl extends QueryController<LoginResponse, LoginRequest> {
//
//    @Override
//    @PostMapping("/sign-in")
//    protected ResponseEntity<LoginResponse> coordinator(@RequestBody LoginRequest loginRequest) {
//        return execute(loginRequest);
//    }
//}
