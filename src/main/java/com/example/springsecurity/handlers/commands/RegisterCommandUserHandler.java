package com.example.springsecurity.handlers.commands;

import com.example.springsecurity.dto.requests.RegisterUserRequest;
import com.example.springsecurity.dto.response.RegisterResponse;
import com.example.springsecurity.mappers.ICommandUserMapper;
import com.example.springsecurity.mappers.IQueryRoleMapper;
import com.example.springsecurity.mappers.IQueryUserMapper;
import io.cqrs.command.ICommandHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.example.springsecurity.models.ERole.*;

@Service
@Transactional
public class RegisterCommandUserHandler implements ICommandHandler<RegisterResponse, RegisterUserRequest> {
    private final PasswordEncoder encoder;
    private final ICommandUserMapper commandUserMapper;
    private final IQueryRoleMapper queryRoleMapper;
    private final IQueryUserMapper queryUserMapper;

    public RegisterCommandUserHandler(PasswordEncoder encoder, ICommandUserMapper commandUserMapper, IQueryRoleMapper queryRoleMapper, IQueryUserMapper queryUserMapper) {
        this.encoder = encoder;
        this.commandUserMapper = commandUserMapper;
        this.queryRoleMapper = queryRoleMapper;
        this.queryUserMapper = queryUserMapper;
    }

    /**
     * register User
     */
    @Override
    public RegisterResponse handler(RegisterUserRequest request) {
        if (queryUserMapper.checkUserExists(request.getUsername())) {
            return new RegisterResponse("Error: Username is already taken!");
        }
        if (queryUserMapper.checkEmailExists(request.getEmail())) {
            return new RegisterResponse("Error: Email is already in use!");
        }
        // Create new user's account
        request.setUuid(UUID.randomUUID());
        request.setPassword(encoder.encode(request.getPassword()));
        request.setRegisteredAt(Timestamp.from(Instant.now()));
        Set<String> strRoles = request.getRoles();
        Set<String> roles = new HashSet<>();

        if (strRoles == null) {
            String userRole = queryRoleMapper.findByRoleName(ROLE_USER.name());
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        String adminRole = queryRoleMapper.findByRoleName(ROLE_ADMIN.getName());
                        roles.add(adminRole);
                        break;
                    case "manage":
                        String modRole = queryRoleMapper.findByRoleName(ROLE_MANAGER.getName());
                        roles.add(modRole);
                        break;
                    default:
                        String userRole = queryRoleMapper.findByRoleName(ROLE_USER.getName());
                        roles.add(userRole);
                }
            });
        }
        request.setRoles(roles);
        commandUserMapper.registerUser(request);
        return new RegisterResponse("User registered successfully! id = " + request.getUuid());
    }
}
