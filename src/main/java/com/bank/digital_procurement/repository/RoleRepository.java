package com.bank.digital_procurement.repository;

import com.bank.digital_procurement.model.Role;
import com.bank.digital_procurement.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
