package com.example.springsecurity.models;

import javax.persistence.*;
import java.rmi.server.UID;


public class Role {
    @Id @Column(name = "roles_uuid", length = 36)
    private UID uuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20)
    private ERole name;
}
