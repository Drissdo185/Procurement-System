package com.system.procurement.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestRegistry {

    private String name;
    private String email;
    private String password;
}
