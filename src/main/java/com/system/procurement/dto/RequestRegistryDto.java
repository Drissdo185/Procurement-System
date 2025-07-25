package com.system.procurement.dto;


import lombok.Data;

@Data
public class RequestRegistryDto {

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
}
