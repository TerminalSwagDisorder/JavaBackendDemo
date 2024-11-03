package com.server.JavaServer.exception;

import com.server.JavaServer.exception.HttpExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class HttpExceptionHandler {
    
    // Handle general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpExceptionMessage> handleException(Exception ex) {
        HttpExceptionMessage errorResponse = new HttpExceptionMessage(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.getMessage(),
            "An unexpected error occurred"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle validation exceptions and extract default messages
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("message", "Validation error");
        response.put("stackTrace", ex.getMessage());

        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage()); // Extract default message
        }
        response.put("errors", fieldErrors); // Include field-specific errors in response

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
