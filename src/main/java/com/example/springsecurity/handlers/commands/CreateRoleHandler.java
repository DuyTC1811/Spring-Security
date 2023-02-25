package com.example.springsecurity.handlers.commands;

import com.example.springsecurity.dto.requests.CreateRoleRequest;
import com.example.springsecurity.dto.response.CreateRoleResponse;
import com.example.springsecurity.mappers.ICommandRoleMapper;
import io.cqrs.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateRoleHandler implements ICommandHandler<CreateRoleResponse, CreateRoleRequest> {
    private final ICommandRoleMapper roleMapper;

    public CreateRoleHandler(ICommandRoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public CreateRoleResponse handler(CreateRoleRequest request) {
        int result = roleMapper.insertRole(request);
        if (result == 1) {
            return CreateRoleResponse.build(request);
        }
        return CreateRoleResponse.builder().build();
    }
}
