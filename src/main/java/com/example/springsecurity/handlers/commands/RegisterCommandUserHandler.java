package com.example.springsecurity.handlers.commands;

import com.example.springsecurity.dto.requests.RegisterUserRequest;
import com.example.springsecurity.dto.response.RegisterUserResponse;
import com.example.springsecurity.mappers.ICommandUserMapper;
import com.example.springsecurity.mappers.IQueryPermissionMapper;
import com.example.springsecurity.mappers.IQueryRoleMapper;
import com.example.springsecurity.mappers.IQueryUserMapper;
import com.example.springsecurity.models.EPermission;
import io.cqrs.command.ICommandHandler;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class RegisterCommandUserHandler implements ICommandHandler<RegisterUserResponse, RegisterUserRequest> {
    private final PasswordEncoder encoder;
    private final ICommandUserMapper commandUserMapper;
    private final IQueryRoleMapper queryRoleMapper;
    private final IQueryUserMapper queryUserMapper;
    private final IQueryPermissionMapper permissionMapper;

    /**
     * register User
     */
    @Override
    public RegisterUserResponse handler(RegisterUserRequest request) {
        if (queryUserMapper.checkUserExists(request.getUsername())) {
            return new RegisterUserResponse("Error: Username is already taken!");
        }
        if (queryUserMapper.checkEmailExists(request.getEmail())) {
            return new RegisterUserResponse("Error: Email is already in use!");
        }
        // Create new user's account
        request.setUuid(UUID.randomUUID().toString());
        request.setPassword(encoder.encode(request.getPassword()));
        request.setRegisteredAt(Timestamp.from(Instant.now()));
        Set<String> strRoles = request.getRolesId();
        Set<String> strPermissions = request.getPermissionId();
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();

        if (strRoles == null) {
            String userRole = queryRoleMapper.findByRoleName(ROLE_USER.name());
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ADMIN":
                        String admin = queryRoleMapper.findByRoleName(ROLE_ADMIN.getName());
                        roles.add(admin);
                        break;
                    case "MANAGER":
                        String manager = queryRoleMapper.findByRoleName(ROLE_MANAGER.getName());
                        roles.add(manager);
                        break;
                    case "USER":
                        String user = queryRoleMapper.findByRoleName(ROLE_MANAGER.getName());
                        roles.add(user);
                    default:
                        new RegisterUserResponse(role + "Role does not exist !");
                }
            });
        }

        if (strPermissions == null) {
            String userPermissions = permissionMapper.findByPermissionName(EPermission.READ.name());
            permissions.add(userPermissions);
        } else {
            strPermissions.forEach(permission -> {
                switch (permission) {
                    case "READ":
                        String read = permissionMapper.findByPermissionName(EPermission.READ.name());
                        permissions.add(read);
                        break;
                    case "WRITE":
                        String write = permissionMapper.findByPermissionName(EPermission.WRITE.name());
                        permissions.add(write);
                        break;
                    case "READ_WRITE":
                        String readWrite = permissionMapper.findByPermissionName(EPermission.READ_WRITE.name());
                        permissions.add(readWrite);
                    default:
                }
            });
        }
        request.setRolesId(roles);
        request.setPermissionId(permissions);
        commandUserMapper.registerUser(request);
        return new RegisterUserResponse("User registered successfully! id = " + request.getUuid());
    }
}
