package com.example.springsecurity.mappers.queries;

import com.example.springsecurity.models.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface IQueryUserMapper {
    UserInfo findByUsername(String request);
    Set<String> findByUserRoles(String request);

    boolean checkUserExists(String username);

    boolean checkEmailExists(String email);

    boolean checkMobileExists(String mobile);
}
