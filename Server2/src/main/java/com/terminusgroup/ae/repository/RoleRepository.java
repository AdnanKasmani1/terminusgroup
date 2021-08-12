package com.terminusgroup.ae.repository;


import com.terminusgroup.ae.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {


    Role findOneById(Long id);



}
