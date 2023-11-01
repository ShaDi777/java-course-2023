package edu.hw4.Validation;

import edu.hw4.Animal;

public class NameAnimalValidator implements AnimalValidator {
    @Override
    public ValidationError getError() {
        return new ValidationError("name", "Name can't be empty");
    }

    @Override
    public ValidationError validate(Animal animal) {
        if (animal == null) {
            return getNullPointerError();
        }

        if (animal.name().isBlank() || animal.name().isEmpty()) {
            return getError();
        }
        return AnimalValidator.getSuccessResult();
    }
}
