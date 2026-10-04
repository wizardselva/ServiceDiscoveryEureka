package com.userauthservice.userauthservice.services;

import com.userauthservice.userauthservice.models.User;
import com.userauthservice.userauthservice.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User getUserDetailsById(Long id){
        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser.isEmpty()){
            return null;
        }
        return optionalUser.get();
    }

}
