package com.bank.digital_procurement.dto.request;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateDepartmentRequest {

    @Size(min = 2, max = 100, message = "Department name must be between 2 and 100 characters")
    private String name;

    private String description;

    private Long parentId;

    private Long managerId;

    @DecimalMin(value = "0.0", inclusive = false, message = "Budget limit must be greater than 0")
    private BigDecimal budgetLimit;

    private String status;
}