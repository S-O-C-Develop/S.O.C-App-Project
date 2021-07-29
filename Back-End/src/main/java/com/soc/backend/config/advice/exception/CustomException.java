package com.soc.backend.config.advice.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    CustomExceptionStatus customExceptionStatus;

}
