package edu.hw2.Task3.Exceptions;

public class ConnectionException extends RuntimeException {
    public ConnectionException() {}

    public ConnectionException(String errorMessage) {
        super(errorMessage);
    }

    public ConnectionException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
