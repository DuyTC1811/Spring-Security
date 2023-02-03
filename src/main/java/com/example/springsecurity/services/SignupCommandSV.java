package com.example.springsecurity.services;

import com.example.springsecurity.dto.requests.SignupRequest;
import com.example.springsecurity.dto.response.SignupResponse;
import com.example.springsecurity.entitys.ERole;
import com.example.springsecurity.entitys.Role;
import com.example.springsecurity.entitys.User;
import com.example.springsecurity.repositorys.IRoleRepository;
import com.example.springsecurity.repositorys.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SignupCommandSV {
    private final PasswordEncoder encoder;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    public SignupCommandSV(PasswordEncoder encoder, IUserRepository userRepository, IRoleRepository roleRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * register User
     */
    public SignupResponse registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new SignupResponse("Error: Username is already taken!");
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new SignupResponse("Error: Email is already in use!");
        }
        /** Create new user's account */
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return new SignupResponse("User registered successfully!");
    }
}
