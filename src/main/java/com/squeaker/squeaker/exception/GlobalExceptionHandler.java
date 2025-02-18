package com.squeaker.squeaker.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler
    public ResponseEntity<SqueakerErrorResponse> handleException(Exception exception){
       SqueakerErrorResponse squeakerErrorResponse = new SqueakerErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
       return new ResponseEntity<>(squeakerErrorResponse, HttpStatus.BAD_REQUEST);
   }


}
