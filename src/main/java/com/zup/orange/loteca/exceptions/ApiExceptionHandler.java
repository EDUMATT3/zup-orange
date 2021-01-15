package com.zup.orange.loteca.exceptions;

import org.hibernate.JDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<Object> handleArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest req){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        ApiException apiException = new ApiException(badRequest.value(), "Invalid argument", message, req.getRequestURI());
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(JDBCException.class)
    public  ResponseEntity<Object> handleDuplicateEmail(JDBCException ex, HttpServletRequest req){
        HttpStatus conflict = HttpStatus.CONFLICT;

        String error = "Unique index or primary key violation";
        String message = "Email is already in use";
        ApiException apiException =  new ApiException(conflict.value(), error, message, req.getRequestURI());
        return new ResponseEntity<>(apiException, conflict);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest req){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(notFound.value(), "Resource not found", ex.getMessage(), req.getRequestURI());
        return new ResponseEntity<>(apiException, notFound);
    }
}
