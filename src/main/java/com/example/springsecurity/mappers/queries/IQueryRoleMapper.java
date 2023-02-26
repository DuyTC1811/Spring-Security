package com.example.springsecurity.mappers.queries;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface IQueryRoleMapper {
    String findByRoleName(String request);
    @MapKey("slug")
    Map<String,String> listRole();
}
