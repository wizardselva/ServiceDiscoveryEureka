package com.userauthservice.userauthservice.repos;

import com.userauthservice.userauthservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

    public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByphoneNumber(String phoneNumber);
//    Optional<User> findByEmailAndPassword(String email,String password);
}
