package com.example.springsecurity.mappers;

import com.example.springsecurity.entitys.ERole;
import com.example.springsecurity.entitys.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, String> {
    @Transactional(readOnly = true)
    @Query("SELECT r FROM Role r WHERE r.name =:name")
    Optional<Role> findByName(ERole name);
}
