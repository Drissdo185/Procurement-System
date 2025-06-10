package com.bank.digital_procurement.repository;

import com.bank.digital_procurement.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bank.digital_procurement.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    boolean existsByUsername(String username);


    List<User> findByDepartmentId(Long departmentId);


    @Query("SELECT u FROM User u WHERE SIZE(u.managedDepartments) > 0")
    List<User> findAllManagers();


    @Query("SELECT u FROM User u JOIN u.roles r WHERE u.department.id = :deptId AND r.name = :roleName")
    List<User> findByDepartmentAndRole(@Param("deptId") Long deptId, @Param("roleName") RoleName roleName);
}
