package com.example.springsecurity.models;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class UserInfo {
    private String userId;
    private String name;
    private String email;
    private String mobile;
    private String username;
    private String lastName;
    private String password;
    private Set<String> roles;
    private String firstName;
    private Timestamp registeredAt;
}
