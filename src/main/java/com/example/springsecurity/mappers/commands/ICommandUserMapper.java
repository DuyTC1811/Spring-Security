package com.example.springsecurity.mappers.commands;

import com.example.springsecurity.dto.requests.RegisterUserRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICommandUserMapper {
    int registerUser(RegisterUserRequest request);
}
