package com.example.springsecurity.mappers;

import com.example.springsecurity.dto.requests.CreateRoleRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ICommandRoleMapper {
    int insertRole(CreateRoleRequest request);
}
