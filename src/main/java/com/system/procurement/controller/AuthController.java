package com.system.procurement.controller;


import com.system.procurement.dto.LoginResponse;
import com.system.procurement.dto.RequestLogin;
import com.system.procurement.dto.RequestRegistry;
import com.system.procurement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RequestRegistry> register(@RequestBody RequestRegistry request) {
        RequestRegistry newUser = userService.register(request);
        return  ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody RequestLogin requestLogin){
        LoginResponse newUser = userService.login(requestLogin);
        return ResponseEntity.ok(newUser);
    }


}
