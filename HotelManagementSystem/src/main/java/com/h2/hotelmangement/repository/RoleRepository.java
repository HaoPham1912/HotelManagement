package com.h2.hotelmangement.repository;

import com.h2.hotelmangement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
