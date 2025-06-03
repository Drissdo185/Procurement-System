package com.bank.digital_procurement.controller;

import com.bank.digital_procurement.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<String>> allAccess() {
        ApiResponse<String> response = ApiResponse.success("This is public content");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse<Map<String, Object>>> userAccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Map<String, Object> userData = Map.of(
                "username", username,
                "message", "This is protected user content",
                "authorities", authentication.getAuthorities()
        );

        ApiResponse<Map<String, Object>> response = ApiResponse.success(userData);
        return ResponseEntity.ok(response);
    }
}

