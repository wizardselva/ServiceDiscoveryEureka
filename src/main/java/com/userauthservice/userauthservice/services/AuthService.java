package com.userauthservice.userauthservice.services;

import com.userauthservice.userauthservice.exceptions.PasswordMismatchException;
import com.userauthservice.userauthservice.exceptions.UserAlreadyExistException;
import com.userauthservice.userauthservice.exceptions.UserNotFoundException;
import com.userauthservice.userauthservice.models.Role;
import com.userauthservice.userauthservice.models.User;
import com.userauthservice.userauthservice.repos.RoleRepo;
import com.userauthservice.userauthservice.repos.UserRepo;
import com.userauthservice.userauthservice.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User signUp(String name, String email, String password, String phoneNumber) {
        Optional<User> userOptional=userRepo.findByEmail(email);
        if(userOptional.isPresent())
            throw new UserAlreadyExistException("User with email "+email+" already exists");
        userOptional=userRepo.findByphoneNumber(phoneNumber);
        if(userOptional.isPresent()){
            throw new UserAlreadyExistException("User with phoneNumber "+phoneNumber+" already exist");
        }
        User user=new User();
        user.setEmail(email);
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        Role role;
        Optional<Role> optionalRole=roleRepo.findByValue("NON_ADMIN");
        if(optionalRole.isEmpty()){
            role=new Role();
            role.setValue("NON_ADMIN");
            roleRepo.save(role);
        }else {
            role=optionalRole.get();
        }
        List<Role> roleList=new ArrayList<>();
        roleList.add(role);
        user.setRoles(roleList);

        return userRepo.save(user);
    }

    @Override
    public User login(String email, String password) {
        Optional<User> userOptional=userRepo.findByEmail(email);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("Please signup first!!!");
        }
        User user=userOptional.get();
//        if(!user.getPassword().equals(password)){
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new PasswordMismatchException("Please Type correct password");
        }
        return user;
    }
}
