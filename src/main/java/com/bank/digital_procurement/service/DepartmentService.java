package com.bank.digital_procurement.service;

import com.bank.digital_procurement.dto.DepartmentDto;
import com.bank.digital_procurement.dto.request.CreateDepartmentRequest;
import com.bank.digital_procurement.dto.request.UpdateDepartmentRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DepartmentService {
    DepartmentDto createDepartment(CreateDepartmentRequest request);

//    List<DepartmentDto> getAllDepartments();
//
//    List<DepartmentDto> getRootDepartments();
//
//    DepartmentDto getDepartmentById(Long id);
//
//    DepartmentDto updateDepartment(Long id, UpdateDepartmentRequest request);
//
//    void deleteDepartment(Long id);
//
//    List<DepartmentDto> getActiveDepartments();
//
//    boolean existsByCode(String code);
//
//    List<Object[]> getDepartmentHierarchy(Long departmentId);



}
