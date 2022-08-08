package com.example.oath2sercurity.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public BadRequestException (String messages) {
        super(messages);
    }

    public BadRequestException(String messages, Throwable cause) {
        super(messages, cause);
    }
}
