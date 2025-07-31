package com.system.procurement.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
public class UserDto {

    @Size(max = 255)
    private String name;
    @Size(max = 255)
    private String email;
    @Size(max = 255)
    private String role;
    private Integer departmentId;
    private String status;
}
