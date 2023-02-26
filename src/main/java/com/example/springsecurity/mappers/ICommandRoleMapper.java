package com.example.springsecurity.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ICommandRoleMapper {
    int insertRole(CreateRoleRequest request);
}
