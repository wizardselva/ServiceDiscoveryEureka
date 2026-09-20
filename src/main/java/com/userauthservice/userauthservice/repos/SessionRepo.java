package com.userauthservice.userauthservice.repos;

import com.userauthservice.userauthservice.models.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepo extends JpaRepository<UserSession, Long> {
    Optional<UserSession> findByToken(String token);
}
