package com.springBoot.userDetails.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserException {
    @ExceptionHandler(value = BadUserDetailsException.class)
    public ResponseEntity<String> BadUserDetailsException(BadUserDetailsException e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
