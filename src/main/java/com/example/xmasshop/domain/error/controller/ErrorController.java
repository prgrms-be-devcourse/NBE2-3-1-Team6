package com.example.xmasshop.domain.error.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = ClassNotFoundException.class)
    public String notFoundError() {
        return "forward:html/error/error.html";
    }


    @ExceptionHandler(value = NoResourceFoundException.class)
    public String noResourceFoundException() {
        return "forward:html/error/error.html";
    }

}
