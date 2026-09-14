package com.userauthservice.userauthservice.repos;

import com.userauthservice.userauthservice.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
