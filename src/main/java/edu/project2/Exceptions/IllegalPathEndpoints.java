package edu.project2.Exceptions;

public class IllegalPathEndpoints extends RuntimeException {
    public IllegalPathEndpoints() {
    }

    public IllegalPathEndpoints(String errorMessage) {
        super(errorMessage);
    }

    public IllegalPathEndpoints(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
