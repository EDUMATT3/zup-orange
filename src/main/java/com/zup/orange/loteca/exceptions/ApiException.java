package com.zup.orange.loteca.exceptions;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
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
}
