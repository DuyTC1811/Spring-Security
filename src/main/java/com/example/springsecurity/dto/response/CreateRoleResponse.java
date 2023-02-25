package com.example.springsecurity.dto.response;

import com.example.springsecurity.dto.requests.CreateRoleRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateRoleResponse {
    private String id;
    private String name;

    public static CreateRoleResponse build(CreateRoleRequest request) {
        return CreateRoleResponse.builder().id(request.getId()).name(request.getRole()).build();
    }
}
