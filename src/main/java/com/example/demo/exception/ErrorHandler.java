package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<CustomError> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(CustomError.builder()
                        .httpStatus(HttpStatus.CONFLICT)
                        .cause(e.getMessage())
                        .dateTime(LocalDateTime.now())
                        .build());
    }
}
