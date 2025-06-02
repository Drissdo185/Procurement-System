package com.bank.digital_procurement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bank.digital_procurement.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findByUsername(String username);
    boolean existsByUsername(String username);

}
