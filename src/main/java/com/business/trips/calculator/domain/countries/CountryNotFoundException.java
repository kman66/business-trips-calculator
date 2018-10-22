package com.business.trips.calculator.domain.countries;

public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException() {
        super();
    }

    public CountryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CountryNotFoundException(String message) {
        super(message);
    }

    public CountryNotFoundException(Throwable cause) {
        super(cause);
    }
}
