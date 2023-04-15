package com.example.springsecurity.dto.response;

import com.example.springsecurity.models.ERole;
import lombok.Data;

@Data
public class FindByRoleNameResponse {
    private ERole role;

}
