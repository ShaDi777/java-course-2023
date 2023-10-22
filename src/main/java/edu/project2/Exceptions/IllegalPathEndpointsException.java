package edu.project2.Exceptions;

public class IllegalPathEndpointsException extends RuntimeException {
    public IllegalPathEndpointsException() {
    }

    public IllegalPathEndpointsException(String errorMessage) {
        super(errorMessage);
    }

    public IllegalPathEndpointsException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
