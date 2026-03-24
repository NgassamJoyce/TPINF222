package com.example.Blog_simple.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseException extends RuntimeException {
    
    public String errorCode;
    private HttpStatus httpStatus;

    public BaseException(String message, String errorCode, HttpStatus httpStatus){
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    
    
}
