package com.system.procurement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email or username is required")
    private String emailOrNTID;

    @NotBlank(message = "Password is required")
    private String password;
}
