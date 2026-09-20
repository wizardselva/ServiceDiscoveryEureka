package com.userauthservice.userauthservice.services;

import com.userauthservice.userauthservice.models.User;

public interface IAuthService {
    User signUp(String name, String email, String password, String phoneNumber);
    User login(String email, String password);
}
