package com.example.gps.exception;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String massage){
        super(massage);
    }
}
