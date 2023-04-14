package com.example.springsecurity.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Document(collection = "users")
public class UserInfo {
    @Id
    private String userId;
    private String name;
    private String userCode;
    private String email;
    private String mobile;
    private String username;
    private String lastName;
    private String password;
    @DBRef
    private Set<Role> roles = new HashSet<>();
    private String firstName;
    private Timestamp registeredAt;
}
