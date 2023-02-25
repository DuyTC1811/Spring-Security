package com.example.springsecurity.dto.requests;

import com.example.springsecurity.dto.response.SignupResponse;
import io.cqrs.command.ICommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
public class SignupRequest implements ICommand<SignupResponse> {
    @NotBlank @Size(min = 3, max = 20)
    private String username;

    @Email @NotBlank @Size(max = 50)
    private String email;

    private Set<String> role;

    @NotBlank @Size(min = 6, max = 40)
    private String password;
}
