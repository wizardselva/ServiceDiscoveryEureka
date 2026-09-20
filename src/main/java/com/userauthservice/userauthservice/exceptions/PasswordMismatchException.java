package com.userauthservice.userauthservice.exceptions;

public class PasswordMismatchException extends RuntimeException{
    public PasswordMismatchException(String msg){
        super(msg);
    }
}
