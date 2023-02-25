package com.example.springsecurity.handlers.commands;

import com.example.springsecurity.dto.requests.RoleRequest;
import com.example.springsecurity.dto.response.RoleResponse;
import com.example.springsecurity.mappers.IRoleMapper;
import io.cqrs.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateRoleHandler implements ICommandHandler<RoleResponse, RoleRequest> {
    private final IRoleMapper roleMapper;

    public CreateRoleHandler(IRoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleResponse handler(RoleRequest request) {
        int result = roleMapper.insertRole(request);
        if (result == 1) {
            return RoleResponse.build(request);
        }
        return RoleResponse.builder().build();
    }
}
