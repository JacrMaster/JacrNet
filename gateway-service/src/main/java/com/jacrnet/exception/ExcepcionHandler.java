package com.jacrnet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExcepcionHandler {
    private final Map<String, Object> errorMap = new HashMap<>();

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<Map<String, Object>> resourceNotFound(TokenException exception){
        errorMap.put("Status", "Error");
        errorMap.put("Message", exception.getMessage());
        errorMap.put("Code", HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(errorMap, HttpStatus.UNAUTHORIZED);
    }
}
