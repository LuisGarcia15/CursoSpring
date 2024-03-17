package com.luis.springboot.error.springbooterror.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String menssage){
        super(menssage);
    }
}
