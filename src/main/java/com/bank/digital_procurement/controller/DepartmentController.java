package com.bank.digital_procurement.controller;

import com.bank.digital_procurement.dto.DepartmentDto;
import com.bank.digital_procurement.dto.request.CreateDepartmentRequest;
import com.bank.digital_procurement.dto.response.ApiResponse;
import com.bank.digital_procurement.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final  DepartmentService departmentService;


    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<DepartmentDto>> createDepartment(@Valid @RequestBody CreateDepartmentRequest departmentDto) {
        DepartmentDto department = departmentService.createDepartment(departmentDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(department));
    }


}
