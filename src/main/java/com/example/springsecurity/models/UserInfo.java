package com.example.springsecurity.models;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Data
public class UserInfo {
    private String uuid;
    private String name;
    private String email;
    private String mobile;
    private String username;
    private String lastName;
    private String password;
    private Set<String> roles;
    private String fistNamle;
    private Timestamp registeredAt;
}
