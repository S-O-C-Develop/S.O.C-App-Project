package com.soc.backend.config.advice;

import com.soc.backend.api.CommonResponse;
import com.soc.backend.config.advice.exception.CustomException;
import com.soc.backend.config.advice.exception.CustomExceptionStatus;
import com.soc.backend.config.advice.exception.CustomDynamicExceptionState;
import com.soc.backend.config.advice.exception.CustomDynamicException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResponse customException(CustomException customException) {
        return new CommonResponse(customException.getCustomExceptionStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResponse customException(CustomDynamicException customDynamicException) {
        return new CommonResponse(customDynamicException.getCustomDynamicExceptionState());
    }

}