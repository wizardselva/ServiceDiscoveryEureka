package com.userauthservice.userauthservice.services;

import com.userauthservice.userauthservice.exceptions.PasswordMismatchException;
import com.userauthservice.userauthservice.exceptions.UserAlreadyExistException;
import com.userauthservice.userauthservice.exceptions.UserNotFoundException;
import com.userauthservice.userauthservice.models.Role;
import com.userauthservice.userauthservice.models.Status;
import com.userauthservice.userauthservice.models.User;
import com.userauthservice.userauthservice.models.UserSession;
import com.userauthservice.userauthservice.repos.RoleRepo;
import com.userauthservice.userauthservice.repos.SessionRepo;
import com.userauthservice.userauthservice.repos.UserRepo;
import com.userauthservice.userauthservice.services.IAuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private SessionRepo sessionRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SecretKey secretKey;

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
    public Pair<User,String> login(String email, String password) {

        Optional<User> userOptional=userRepo.findByEmail(email);

        if(!userOptional.isPresent()){
            throw new UserNotFoundException("Please signup first!!!");
        }

        User user=userOptional.get();

//        if(!user.getPassword().equals(password)){
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new PasswordMismatchException("Please Type correct password");
        }

        //Generate JWT.. Header,Payload,Signature
        Map<String, Object> claims = new HashMap<>();

        claims.put("user_id", user.getId());
        claims.put("issuer", "scaler");
        Long currentTime=System.currentTimeMillis();
        claims.put("iat", currentTime);
        claims.put("exp", currentTime + 100000);
        List<String> roles=new ArrayList<>();
        for(Role role:user.getRoles()){
            roles.add(role.getValue());
        }
        claims.put("access", roles);

//        MacAlgorithm algorithm=Jwts.SIG.HS256;
//        SecretKey secretKey=algorithm.key().build();

        String token= Jwts.builder().claims(claims).signWith(secretKey).compact();

        UserSession userSession=new UserSession();
        userSession.setToken(token);
        userSession.setUser(user);
        sessionRepo.save(userSession);

        return new Pair<>(user,token);
    }

    //Validation
    public Boolean validateToken(String token){
        Optional<UserSession> optionalUserSession=sessionRepo.findByToken(token);

        if(optionalUserSession.isEmpty()){
            return false;
        }
        JwtParser jwtParser=Jwts.parser().verifyWith(secretKey).build();
        Claims claims=jwtParser.parseSignedClaims(token).getPayload();

        Long expiry=(Long)claims.get("exp");
        Long currentTime=System.currentTimeMillis();

        System.out.println("expiry:"+expiry);
        System.out.println("currentTime:"+currentTime);

        if(currentTime > expiry){
            UserSession userSession=optionalUserSession.get();
//            userSession1.setStatus(Status.INACTIVE);
//            sessionRepo.save(userSession1);
                sessionRepo.deleteById(optionalUserSession.get().getId());

            System.out.println("Token has expired");
            return false;
        }
        return true;
    }
}
