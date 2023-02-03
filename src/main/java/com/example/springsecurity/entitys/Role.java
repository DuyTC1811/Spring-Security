package com.example.springsecurity.entitys;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
//    @Type(type="uuid-char")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roles_uuid", length = 36)
    private String uuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20)
    private ERole name;
}
