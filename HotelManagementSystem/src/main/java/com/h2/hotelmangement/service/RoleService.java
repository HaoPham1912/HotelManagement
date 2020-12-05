package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleService{
    List<Role> findAllRole();
    Role findByRoleName(String roleName);
}
