package com.example.springsecurity.controllers.commands;

import com.example.springsecurity.dto.requests.RoleRequest;
import com.example.springsecurity.dto.response.RoleResponse;
import io.cqrs.controller.CommandController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
@Tag(name = "API role", description = "Role management")
public class CreateRoleCommandCtrl extends CommandController<RoleResponse, RoleRequest> {

    @Override
    @PostMapping("/create-role")
    protected ResponseEntity<RoleResponse> coordinator(@RequestBody RoleRequest request) {
        return execute(request);
    }
}
