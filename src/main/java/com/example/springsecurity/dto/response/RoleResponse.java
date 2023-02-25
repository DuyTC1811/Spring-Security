package com.example.springsecurity.dto.response;

import com.example.springsecurity.dto.requests.RoleRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleResponse {
    private String id;
    private String name;

    public static RoleResponse build(RoleRequest request) {
        return RoleResponse.builder().id(request.getId()).name(request.getRole()).build();
    }
}
