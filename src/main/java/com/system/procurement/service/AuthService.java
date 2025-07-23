package com.system.procurement.service;

import com.system.procurement.dto.LoginRequest;
import com.system.procurement.dto.RegisterRequest;
import com.system.procurement.dto.UserProfileDto;

public interface AuthService {
    UserProfileDto register(RegisterRequest registerRequest);
    UserProfileDto login(LoginRequest loginRequest);
    void logout(String token);
    boolean validateToken(String token);
    Long getUserIdFromToken(String token);
}
