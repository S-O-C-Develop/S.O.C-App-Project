package com.soc.BackEnd.config.advice;

import com.soc.BackEnd.api.CommonResponse;
import com.soc.BackEnd.api.ResponseService;
import com.soc.BackEnd.config.advice.exception.CustomUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResponse defaultException(HttpServletRequest req, Exception e){
        return responseService.getFailResponse();
    }

    @ExceptionHandler(CustomUserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResponse userNotFoundException(HttpServletRequest req, CustomUserNotFoundException e){
        return responseService.getFailResponse();
    }

}