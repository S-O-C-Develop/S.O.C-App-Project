package com.soc.backend.utils;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class CustomParsing {
    public static String getErrorString(Errors errors) {
        StringBuilder builder = new StringBuilder();
        for (ObjectError error : errors.getAllErrors()) {
            builder.append(error.getDefaultMessage());
            builder.append('\n');
        }
        return builder.toString();
    }
}
