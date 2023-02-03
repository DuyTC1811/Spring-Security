package com.example.springsecurity.dto;

import io.cqrs.response.BaseResponse;
import lombok.Data;

@Data
public class OpenAccountResponse extends BaseResponse {
    private String id;

    public OpenAccountResponse(String message, String id) {
        super(message);
        this.id = id;
    }
}
