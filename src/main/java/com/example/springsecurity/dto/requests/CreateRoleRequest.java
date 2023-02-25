package com.example.springsecurity.dto.requests;

import com.example.springsecurity.dto.response.CreateRoleResponse;
import io.cqrs.command.ICommand;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateRoleRequest implements ICommand<CreateRoleResponse> {
    private String id = UUID.randomUUID().toString();
    private String role;
}
