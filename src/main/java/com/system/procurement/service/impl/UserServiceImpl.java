package com.system.procurement.service.impl;

import com.system.procurement.dto.RequestRegistryDto;
import com.system.procurement.entity.User;
import com.system.procurement.exception.UserAlreadyExistsException;
import com.system.procurement.repository.UserRepository;
import com.system.procurement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public RequestRegistryDto register(RequestRegistryDto requestRegistryDto) {
        if(userRepository.existsByEmail(requestRegistryDto.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + requestRegistryDto.getEmail() + " already exists");
        }
        User newUser = new User();
        newUser.setUsername(requestRegistryDto.getUsername());
        newUser.setEmail(requestRegistryDto.getEmail());
        // Hashing password
        String hashedPassword = passwordEncoder.encode(requestRegistryDto.getPassword());
        newUser.setPasswordHash(hashedPassword);
        newUser.setFirstName(requestRegistryDto.getFirstName());
        newUser.setLastName(requestRegistryDto.getLastName());
        newUser.setPhone(requestRegistryDto.getPhone());
        userRepository.save(newUser);

        return requestRegistryDto;


    }
}
