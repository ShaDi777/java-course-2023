package edu.hw4.Validation;

public record ValidationError(
    String fieldName,
    String errorMessage
) {
}
