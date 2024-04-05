package jjad.springframework.api.v1.controller;

import jjad.springframework.services.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handleNotFoundException(Exception exception, WebRequest request){

        return "Resource Not Found";

    }

}
