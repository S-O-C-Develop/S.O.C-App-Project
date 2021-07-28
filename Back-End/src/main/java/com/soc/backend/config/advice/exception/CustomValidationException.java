package com.soc.backend.config.advice.exception;

public class CustomValidationException extends RuntimeException{
    public CustomValidationException() {
        super();
    }

    public CustomValidationException(String message) {
        super(message);
    }

    public CustomValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
