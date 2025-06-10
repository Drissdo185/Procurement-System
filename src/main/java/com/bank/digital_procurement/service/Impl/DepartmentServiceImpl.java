package com.bank.digital_procurement.service.Impl;

import com.bank.digital_procurement.dto.DepartmentDto;
import com.bank.digital_procurement.dto.request.CreateDepartmentRequest;
import com.bank.digital_procurement.exception.AppException;
import com.bank.digital_procurement.exception.ErrorCode;
import com.bank.digital_procurement.model.Department;
import com.bank.digital_procurement.model.DepartmentStatus;
import com.bank.digital_procurement.model.User;
import com.bank.digital_procurement.repository.DepartmentRepository;
import com.bank.digital_procurement.repository.UserRepository;
import com.bank.digital_procurement.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.bank.digital_procurement.mapper.DepartmentMapper.toDto;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public DepartmentDto createDepartment(CreateDepartmentRequest request){
        if(departmentRepository.existsByCode(request.getCode())){
            throw new AppException(ErrorCode.DEPARTMENT_CODE_EXISTED);
        }

        Department department = new Department();
        department.setCode(request.getCode());
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        department.setBudgetLimit(request.getBudgetLimit());
        department.setStatus(DepartmentStatus.ACTIVE);

        if(request.getParentId() != null){
            Department parent = departmentRepository.findById(request.getParentId()).orElseThrow(
                    () -> new AppException(ErrorCode.INVALID_PARENT_DEPARTMENT)
            );
            department.setParent(parent);
        }

        if(request.getManagerId() != null){
            User manager = userRepository.findById(request.getManagerId()).orElseThrow(
                    () -> new AppException(ErrorCode.MANAGER_NOT_FOUND)
            );
            department.setManager(manager);
        }
        Department savedDepartment = departmentRepository.save(department);

        return toDto(savedDepartment);
    }
}
