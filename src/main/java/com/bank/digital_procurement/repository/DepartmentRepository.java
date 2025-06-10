package com.bank.digital_procurement.repository;

import com.bank.digital_procurement.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByCode(String code);

    boolean existsByCode(String code);

    List<Department> findByParentIsNull();

    List<Department> findByParentId(Long parentId);

  
    List<Department> findByManagerId(Long managerId);


    @Query("SELECT d FROM Department d WHERE d.status = 'ACTIVE'")
    List<Department> findAllActive();


    @Query("SELECT d FROM Department d WHERE d.id = :deptId OR d.manager.id = :userId")
    List<Department> findDepartmentsUserCanSubmitTo(@Param("deptId") Long deptId, @Param("userId") Long userId);




}
