package com.vmi.planning.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice(basePackages = {"com.vmi.planning.Controllers"})
public class RestControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Exception> handleUnexpectedExceptions(){

        Exception exception = new Exception("not allowed");

        return new ResponseEntity<Exception>(exception, INTERNAL_SERVER_ERROR);
    }
}
