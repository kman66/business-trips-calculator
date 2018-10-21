package com.business.trips.calculator.domain.parameters;

public class ParameterNotFoundException extends RuntimeException {

    public ParameterNotFoundException() {
        super();
    }

    public ParameterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterNotFoundException(String message) {
        super(message);
    }

    public ParameterNotFoundException(Throwable cause) {
        super(cause);
    }
}
