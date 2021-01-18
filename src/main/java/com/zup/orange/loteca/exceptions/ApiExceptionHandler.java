package com.zup.orange.loteca.exceptions;

import org.hibernate.JDBCException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, HttpServletRequest req){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        ApiException apiException = new ApiException(badRequest.value(), "Invalid argument", message, req.getRequestURI());
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundExceptionHandler(UserNotFoundException ex, HttpServletRequest req){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(notFound.value(), "Resource not found", ex.getMessage(), req.getRequestURI());
        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex, HttpServletRequest req){
        HttpStatus notFound = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(notFound.value(), "Body not match", ex.getMessage(), req.getRequestURI());
        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> runtimeExceptionHandler(RuntimeException ex, HttpServletRequest req) {
        HttpStatus notFound = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(notFound.value(), "Error on the server", ex.getMessage(), req.getRequestURI());
        return new ResponseEntity<>(apiException, notFound);
    }
}
