package com.zup.orange.loteca.exception;

import org.hibernate.JDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public  ResponseEntity<Object> handleArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest wr){
       System.out.println("PATH ==> " + wr.getContextPath());
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        ApiException apiException = new ApiException(message, badRequest, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {JDBCException.class})
    public  ResponseEntity<Object> hhandleNotFoundValidException(JDBCException ex){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        String message = ex.getSQLException().getLocalizedMessage();
        ApiException apiException = new ApiException(message, badRequest, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public  ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex){
        HttpStatus badRequest = HttpStatus.NOT_FOUND;

        String message = ex.getMessage();
        ApiException apiException = new ApiException(message, badRequest, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, badRequest);
    }
}
