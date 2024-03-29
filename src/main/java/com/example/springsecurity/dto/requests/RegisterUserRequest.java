package com.example.springsecurity.dto.requests;

import com.example.springsecurity.dto.response.RegisterUserResponse;
import io.cqrs.command.ICommand;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Set;

@Setter
@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
public class RegisterUserRequest implements ICommand<RegisterUserResponse> {
    private String uuid;
    @NotBlank
    @Size(max = 50)
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private String email;
    private String mobile;
    private String username;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    private Set<String> rolesId;
    private Timestamp registeredAt;
}
