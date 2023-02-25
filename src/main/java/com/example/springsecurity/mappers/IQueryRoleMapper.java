package com.example.springsecurity.mappers;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IQueryRoleMapper {
    String findByRoleName(String request);
}
