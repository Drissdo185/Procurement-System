package com.system.procurement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)

    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255)

    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 255)

    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 50)

    @Column(name = "role", nullable = false, length = 50)
    private String role;

    @Column(name = "department_id")
    private Integer departmentId;

    @Size(max = 20)

    @ColumnDefault("pending")
    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

}