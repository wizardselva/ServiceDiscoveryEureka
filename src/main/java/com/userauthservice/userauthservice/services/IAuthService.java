package com.userauthservice.userauthservice.services;

import com.userauthservice.userauthservice.models.User;
import org.antlr.v4.runtime.misc.Pair;

public interface IAuthService {
    User signUp(String name, String email, String password, String phoneNumber);
    Pair<User,String> login(String email, String password);
    public Boolean validateToken(String token);
}
