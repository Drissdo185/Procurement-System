package com.system.procurement.service.impl;

import com.system.procurement.dto.LoginResponse;
import com.system.procurement.dto.RequestLogin;
import com.system.procurement.dto.RequestRegistry;
import com.system.procurement.dto.UserDto;
import com.system.procurement.entity.User;
import com.system.procurement.exception.UserAlreadyExistsException;
import com.system.procurement.repository.UserRepository;
import com.system.procurement.service.UserService;
import com.system.procurement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        newUser.setName(requestRegistry.getName());
        newUser.setEmail(requestRegistry.getEmail());
        // Hashing password
        String hashedPassword = passwordEncoder.encode(requestRegistry.getPassword());
        newUser.setPassword(hashedPassword);
        newUser.setRole("user");
        newUser.setStatus("pending");
        newUser.setCreatedAt(java.time.LocalDateTime.now());

        userRepository.save(newUser);

        return requestRegistry;
    }

    @Override
    public LoginResponse login(RequestLogin requestLogin) {
        User user = userRepository.findByEmail(requestLogin.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(requestLogin.getPassword(), user.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }
        String token = jwtUtil.createToken(user.getEmail());
        return new LoginResponse(token, user.getName());

    }

    @Override
    public UserDto getUserByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        userDto.setDepartmentId(user.getDepartmentId());
        userDto.setStatus(user.getStatus());
        return userDto;
    }
}
