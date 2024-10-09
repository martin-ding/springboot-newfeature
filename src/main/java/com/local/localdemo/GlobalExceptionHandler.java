package com.local.localdemo;

import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleError(Exception ex) {
        // recording exception and  Correlation ID
        System.err.println("Error occurred: " + ex.getMessage() + ", Correlation ID: " + MDC.get("correlationId"));
    }
}
