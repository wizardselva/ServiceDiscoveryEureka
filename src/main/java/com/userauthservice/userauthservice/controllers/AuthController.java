package com.userauthservice.userauthservice.controllers;

import com.userauthservice.userauthservice.dtos.LoginRequestDto;
import com.userauthservice.userauthservice.dtos.SignupRequestDto;
import com.userauthservice.userauthservice.dtos.UserDto;
import com.userauthservice.userauthservice.dtos.ValidateTokenRequestDto;
import com.userauthservice.userauthservice.models.Role;
import com.userauthservice.userauthservice.models.User;
import com.userauthservice.userauthservice.services.IAuthService;
import org.antlr.v4.runtime.misc.MultiMap;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;
    //signup

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupRequestDto signupRequestDto){
        User user=authService.signUp(signupRequestDto.getName(),
                signupRequestDto.getEmail(),
                signupRequestDto.getPassword(),
                signupRequestDto.getPhoneNumber());

        UserDto userDto=from(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/validateToken")
    public Boolean validateToken(@RequestBody ValidateTokenRequestDto validateTokenRequestDto){
        return authService.validateToken(validateTokenRequestDto.getToken());
    }

    private UserDto from(User user){
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        List<String> roleValues=new ArrayList<>();
        for(Role role:user.getRoles()){
            roleValues.add(role.getValue());
        }
        userDto.setRoles(roleValues);
        return userDto;
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto){
        Pair<User,String> response=authService.login(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword()
        );

        User user=response.a;
        String token=response.b;

        UserDto userDto=from(user);

        MultiValueMap<String,String> headers=new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.SET_COOKIE,"auth_session_id="+token);

        return new ResponseEntity<>(userDto,headers,HttpStatus.OK);
    }


}
