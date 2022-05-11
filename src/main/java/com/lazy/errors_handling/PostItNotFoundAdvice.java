package com.lazy.errors_handling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PostItNotFoundAdvice {
      
      @ResponseBody
      @ExceptionHandler(PostItNotFoundException.class)
      @ResponseStatus(HttpStatus.NOT_FOUND)
      String postItNotFoundHandler(PostItNotFoundException ex){
            return ex.getMessage();
      }
}
