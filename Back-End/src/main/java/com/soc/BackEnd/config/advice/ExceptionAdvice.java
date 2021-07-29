package com.soc.backend.config.advice;

import com.soc.backend.api.CommonResponse;
import com.soc.backend.config.advice.exception.CustomException;
import com.soc.backend.config.advice.exception.CustomDynamicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResponse customException(CustomException customException) {
        log.warn(customException.getCustomExceptionStatus().getMessage());
        return new CommonResponse(customException.getCustomExceptionStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResponse customException(CustomDynamicException customDynamicException) {
        log.warn(customDynamicException.getCustomDynamicExceptionState().getMessage());
        return new CommonResponse(customDynamicException.getCustomDynamicExceptionState());
    }

}