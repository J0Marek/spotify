package com.selnix.spotify.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityDoesNotExistException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleEntityDoesNotExistException(EntityDoesNotExistException e) {
        return e.getMessage();
    }
}