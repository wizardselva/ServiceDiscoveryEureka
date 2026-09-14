package com.userauthservice.userauthservice.controllers;

import com.userauthservice.userauthservice.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    //signup

    @PostMapping
    public ResponseEntity<UserDto> signUp(@RequestBody SignupRequestDto signupRequestDto){

    }

    //login
}
