package com.example.springsecurity.mappers;

import com.example.springsecurity.dto.requests.RoleRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IRoleMapper {
    int insertRole(RoleRequest request);
}
