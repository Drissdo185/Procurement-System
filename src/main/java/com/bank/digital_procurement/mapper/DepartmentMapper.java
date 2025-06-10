package com.bank.digital_procurement.mapper;

import com.bank.digital_procurement.dto.DepartmentDto;
import com.bank.digital_procurement.model.Department;

import java.util.Set;
import java.util.stream.Collectors;

public class DepartmentMapper {

    public static DepartmentDto toDto(Department department) {
        if (department == null) {
            return null;
        }

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setCode(department.getCode());
        departmentDto.setName(department.getName());
        departmentDto.setDescription(department.getDescription());

        if (department.getParent() != null) {
            departmentDto.setParentId(department.getParent().getId());
        }

        if (department.getManager() != null) {
            departmentDto.setManagerId(department.getManager().getId());
        }

        departmentDto.setBudgetLimit(department.getBudgetLimit());
        departmentDto.setSpentAmount(department.getSpentAmount());
        departmentDto.setRemainingBudget(department.getRemainingBudget());
        departmentDto.setStatus(department.getStatus());
        departmentDto.setCreatedAt(department.getCreatedAt());
        departmentDto.setUpdatedAt(department.getUpdatedAt());

        if (department.getChildren() != null) {
            Set<Long> childrenIds = department.getChildren()
                    .stream()
                    .map(Department::getId)
                    .collect(Collectors.toSet());
            departmentDto.setChildrenIds(childrenIds);
        }

        return departmentDto;
    }

    public static Department toEntity(DepartmentDto dto) {
        if (dto == null) {
            return null;
        }

        Department department = new Department();
        department.setId(dto.getId());
        department.setCode(dto.getCode());
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());

        // Parent and Manager references should be set in the service layer
        // after fetching the corresponding entities from the database.
        // Here we just set the IDs as placeholders; actual linking is done elsewhere.
        if (dto.getParentId() != null) {
            Department parent = new Department();
            parent.setId(dto.getParentId());
            department.setParent(parent);
        }

        if (dto.getManagerId() != null) {
            // You probably have a User entity; here we only set the ID as a placeholder.
            // The actual User object should be fetched from the database in the service.
            var manager = new com.bank.digital_procurement.model.User();
            manager.setId(dto.getManagerId());
            department.setManager(manager);
        }

        department.setBudgetLimit(dto.getBudgetLimit());
        department.setSpentAmount(dto.getSpentAmount());
        department.setStatus(dto.getStatus());
        department.setCreatedAt(dto.getCreatedAt());
        department.setUpdatedAt(dto.getUpdatedAt());

        // children field is ignored to avoid circular references or recursion

        return department;
    }
}
