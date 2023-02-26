package com.example.springsecurity.models;

public enum EPermission {
    READ("READ"),
    WRITE("WRITE"),
    READ_WRITE("READ_WRITE");
    private final String name;

    EPermission(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
