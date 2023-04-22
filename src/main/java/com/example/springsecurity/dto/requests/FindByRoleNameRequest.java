package com.example.springsecurity.dto.requests;

import com.example.springsecurity.dto.response.FindByRoleNameResponse;
import com.example.springsecurity.models.ERole;
import io.cqrs.query.IQuery;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FindByRoleNameRequest implements IQuery<FindByRoleNameResponse> {
    private ERole roleName;

}
