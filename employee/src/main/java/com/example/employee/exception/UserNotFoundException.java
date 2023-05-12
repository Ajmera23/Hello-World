package com.example.employee.exception;


public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super(message);
    }
}
