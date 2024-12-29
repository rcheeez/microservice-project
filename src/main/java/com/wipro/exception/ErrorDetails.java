package com.wipro.exception;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ErrorDetails {
    
    private LocalDate timestamp;
    private String message;
    private int httpCode;
    private String description;
}
