package com.example.springsecurity.mappers;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface IQueryPermissionMapper {
    @MapKey("slug")
    Map<String, String> listPermission();

    String findByPermissionName(String name);
}
