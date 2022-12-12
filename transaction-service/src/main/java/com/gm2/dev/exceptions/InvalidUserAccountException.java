package com.gm2.dev.exceptions;

public class InvalidUserAccountException extends RuntimeException {

    public InvalidUserAccountException(String message){
        super(message);
    }
}
