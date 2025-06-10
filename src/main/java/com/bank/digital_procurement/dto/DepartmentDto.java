package com.bank.digital_procurement.dto;

import com.bank.digital_procurement.model.DepartmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for Department entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long id;
    private String code;
    private String name;
    private String description;

    private Long parentId;

    private Long managerId;

    private BigDecimal budgetLimit;
    private BigDecimal spentAmount;
    private BigDecimal remainingBudget;

    private DepartmentStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Set<Long> childrenIds;

}
