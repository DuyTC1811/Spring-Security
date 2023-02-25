package com.example.springsecurity.dto.requests;

import com.example.springsecurity.dto.response.RoleResponse;
import io.cqrs.command.ICommand;
import lombok.Data;

import java.util.UUID;

@Data
public class RoleRequest implements ICommand<RoleResponse> {
    private String id = UUID.randomUUID().toString();
    private String role;
}
