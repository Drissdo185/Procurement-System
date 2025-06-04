package com.bank.digital_procurement.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;
    private String code;
    private String name;
    private String description;
    private Long parentId;
    private String parentName;
    private Long managerId;
    private String managerName;
    private BigDecimal budgetLimit;
    private BigDecimal spentAmount;
    private BigDecimal remainingBudget;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<DepartmentDto> children;
    private Integer totalUsers;
}
