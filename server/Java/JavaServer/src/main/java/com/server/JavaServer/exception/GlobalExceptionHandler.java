package com.server.JavaServer.exception;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(Exception.class)
 public ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request){
     return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
 }
}
