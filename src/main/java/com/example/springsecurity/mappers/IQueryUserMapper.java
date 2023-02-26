package com.example.springsecurity.mappers;

import com.example.springsecurity.models.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IQueryUserMapper {
    UserInfo findByUsername(String request);

    boolean checkUserExists(String username);

    boolean checkEmailExists(String email);

    boolean checkMobileExists(String email);
}
