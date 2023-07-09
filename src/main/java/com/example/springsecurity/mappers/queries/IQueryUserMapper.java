package com.example.springsecurity.mappers.queries;

import com.example.springsecurity.models.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IQueryUserMapper {
    UserInfo findByUsername(String request);

}
