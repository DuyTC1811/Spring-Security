package com.example.springsecurity.dto.requests;

import com.example.springsecurity.dto.response.RegisterResponse;
import io.cqrs.command.ICommand;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class RegisterUserRequest implements ICommand<RegisterResponse> {
    private UUID uuid;
    @NotBlank @Size(min = 3, max = 20)
    private String name;
    @NotBlank @Size(max = 50)
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private String email;
    private String mobile;
    private String username;
    private String lastName;
    @NotBlank @Size(min = 6, max = 40)
    private String password;
    private Set<String> roles;
    private String fistNamle;
    private Timestamp registeredAt;
}
