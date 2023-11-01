package edu.hw4.Validation;

import edu.hw4.Animal;

public interface AnimalValidator {
    default ValidationError getNullPointerError() {
        return new ValidationError("Animal", "Animal is null");
    }

    static ValidationError getSuccessResult() {
        return new ValidationError("", "Success validation");
    }

    ValidationError getError();

    ValidationError validate(Animal animal);
}
