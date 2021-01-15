package com.zup.orange.loteca.exception;

import org.hibernate.JDBCException;

import java.sql.SQLException;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
