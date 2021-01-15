package com.zup.orange.loteca.exceptions;
import java.time.ZonedDateTime;

public class ApiException {
    ZonedDateTime timestamp;
    int status;
    String error;
    String message;
    String path;

    public ApiException(int status, String error, String message, String path) {
        this.timestamp = ZonedDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() { return path; }

    public int getStatus() { return status;}

    public String getError() { return error; }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
