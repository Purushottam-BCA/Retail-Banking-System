package com.cognizant.authenticationservice.errorhandling;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage 
{
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
}