package com.example.demo.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class CustomError {
    private String cause;
    private HttpStatus httpStatus;
    private LocalDateTime dateTime;
}
