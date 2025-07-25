package com.system.procurement.dto;


import lombok.Data;

@Data
public class UserDto {

    private String username;
    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String phone;
}
