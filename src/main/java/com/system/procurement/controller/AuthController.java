package com.system.procurement.controller;


import com.system.procurement.dto.RequestRegistryDto;
import com.system.procurement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RequestRegistryDto> register(@RequestBody RequestRegistryDto request) {
        RequestRegistryDto newUser = userService.register(request);
        return  ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }


}
