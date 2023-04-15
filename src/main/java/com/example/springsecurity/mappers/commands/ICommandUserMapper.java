package com.example.springsecurity.mappers.commands;

import com.example.springsecurity.dto.requests.RegisterUserRequest;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.Map;

@Mapper
public interface ICommandUserMapper {
    int registerUser(RegisterUserRequest request);

    int lastLogin(Map<String, Object> param);
}
