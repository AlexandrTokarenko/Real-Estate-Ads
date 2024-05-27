package com.example.realestateads.exception;

public class UserAlreadyExistException extends Exception{

    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
