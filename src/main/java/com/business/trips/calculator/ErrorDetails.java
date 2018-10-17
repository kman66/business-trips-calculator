package com.business.trips.calculator;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ErrorDetails(final LocalDateTime timestamp, final String message, final String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
