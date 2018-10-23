package com.business.trips.calculator.domain.projects;

public class ProjectNotFoundException extends RuntimeException {

    public ProjectNotFoundException() {
        super();
    }

    public ProjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectNotFoundException(String message) {
        super(message);
    }

    public ProjectNotFoundException(Throwable cause) {
        super(cause);
    }
}
