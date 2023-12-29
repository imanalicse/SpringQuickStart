package com.imanali.SpringQuickStart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        response.put("status", false);
        response.put("status_code", HttpStatus.BAD_REQUEST.value());
        response.put("errors", errors);
        return response;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, Object> handleBusinessException(UserNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("status_code", HttpStatus.NOT_FOUND.value());
        response.put("message", ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public Map<String, Object> handleUnauthorizedException(UnauthorizedException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("status_code", HttpStatus.UNAUTHORIZED.value());
        response.put("message", exception.getMessage());
        return response;
    }
}
