//package com.example.springsecurity.handlers.commands;
//
//import com.example.springsecurity.dto.requests.RegisterUserRequest;
//import com.example.springsecurity.dto.response.RegisterUserResponse;
//import com.example.springsecurity.mappers.commands.ICommandUserMapper;
//import com.example.springsecurity.mappers.queries.IQueryRoleMapper;
//import com.example.springsecurity.mappers.queries.IQueryUserMapper;
//import io.cqrs.command.ICommandHandler;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.sql.Timestamp;
//import java.time.Instant;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.UUID;
//
//import static com.example.springsecurity.models.ERole.*;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class RegisterCommandUserHandler implements ICommandHandler<RegisterUserResponse, RegisterUserRequest> {
//    private final PasswordEncoder encoder;
//    private final IQueryRoleMapper queryRoleMapper;
//    private final IQueryUserMapper queryUserMapper;
//    private final ICommandUserMapper commandUserMapper;
//
//    /**
//     * register User
//     */
//    @Override
//    public RegisterUserResponse handler(RegisterUserRequest request) {
//        if (queryUserMapper.checkUserExists(request.getUsername())) {
//            return new RegisterUserResponse("Error: Username is already taken!");
//        }
//        if (queryUserMapper.checkEmailExists(request.getEmail())) {
//            return new RegisterUserResponse("Error: Email is already in use!");
//        }
//        if (queryUserMapper.checkMobileExists(request.getMobile())) {
//            return new RegisterUserResponse("Error: Phone is already in use!");
//        }
//        // Create new user's account
//        request.setUuid(UUID.randomUUID().toString());
//        request.setPassword(encoder.encode(request.getPassword()));
//        request.setRegisteredAt(Timestamp.from(Instant.now()));
//        Set<String> strRoles = request.getRolesId();
//        Set<String> roles = new HashSet<>();
//
//        if (strRoles == null) {
//            String userRole = queryRoleMapper.findByRoleName(ROLE_USER.name());
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "ADMIN": String admin = queryRoleMapper.findByRoleName(ROLE_ADMIN.getName());
//                        roles.add(admin);
//                        break;
//                    case "MANAGER": String manager = queryRoleMapper.findByRoleName(ROLE_MANAGER.getName());
//                        roles.add(manager);
//                        break;
//                    case "USER": String user = queryRoleMapper.findByRoleName(ROLE_USER.getName());
//                        roles.add(user);
//                        break;
//                    default:
//                        new RegisterUserResponse(role + " Role does not exist !");
//                }
//            });
//        }
//        request.setRolesId(roles);
//        commandUserMapper.registerUser(request);
//        return new RegisterUserResponse("User registered successfully! id = " + request.getUuid());
//    }
//
//}
