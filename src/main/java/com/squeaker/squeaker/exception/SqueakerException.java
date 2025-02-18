package com.squeaker.squeaker.exception;

import org.springframework.http.HttpStatus;

public class SqueakerException extends RuntimeException {
    private HttpStatus httpStatus;

    public SqueakerException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
