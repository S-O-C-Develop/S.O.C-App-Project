package com.soc.backend.config.advice.exception;


import lombok.Getter;

@Getter
public class CustomDynamicExceptionState {
    private final boolean isSuccess;
    private final int code;
    private final String message;

    public CustomDynamicExceptionState(int code, String message) {
        this.isSuccess = false;
        this.code = code;
        this.message = message;
    }
}
