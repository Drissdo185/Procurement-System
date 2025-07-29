package com.system.procurement.service.impl;

import com.system.procurement.dto.LoginResponse;
import com.system.procurement.dto.RequestLogin;
import com.system.procurement.dto.RequestRegistry;
import com.system.procurement.entity.User;
import com.system.procurement.exception.UserAlreadyExistsException;
import com.system.procurement.repository.UserRepository;
import com.system.procurement.service.UserService;
import com.system.procurement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public RequestRegistry register(RequestRegistry requestRegistry) {
        if(userRepository.existsByEmail(requestRegistry.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + requestRegistry.getEmail() + " already exists");
        }
        User newUser = new User();
        newUser.setUsername(requestRegistry.getUsername());
        newUser.setEmail(requestRegistry.getEmail());
        // Hashing password
        String hashedPassword = passwordEncoder.encode(requestRegistry.getPassword());
        newUser.setPasswordHash(hashedPassword);
        newUser.setFirstName(requestRegistry.getFirstName());
        newUser.setLastName(requestRegistry.getLastName());
        newUser.setPhone(requestRegistry.getPhone());
        userRepository.save(newUser);

        return requestRegistry;
    }

    @Override
    public LoginResponse login(RequestLogin requestLogin) {
        User user = userRepository.findByEmail(requestLogin.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(requestLogin.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Incorrect password");
        }
        String token = jwtUtil.createToken(user.getEmail());
        return new LoginResponse(token, user.getUsername());

    }
}
