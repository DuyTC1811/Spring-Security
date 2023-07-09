package com.example.springsecurity.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserInfo {
    private String userId;
    private String userCode;
    private String email;
    private String mobile;
    private String username;
    private String lastName;
    private String password;
    private Set<String> roles = new HashSet<>();
    private String firstName;
    private Timestamp registeredAt;

}
