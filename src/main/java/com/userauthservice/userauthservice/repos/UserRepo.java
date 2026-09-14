package com.userauthservice.userauthservice.repos;

import com.userauthservice.userauthservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
