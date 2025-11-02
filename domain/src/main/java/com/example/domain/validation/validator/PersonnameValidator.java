package com.example.domain.validation.validator;

public class PersonnameValidator extends AbstractValidator {
    private final String returnMessage;

    public PersonnameValidator(Object fieldValue) {
        this.fieldValue = fieldValue;
        this.returnMessage = "Name must contain only letters and spaces.";
    }

    @Override
    public String validate() {
        String username = (String) fieldValue;
        if (!username.matches("^[a-zA-Z\\s'-.]+$"))
            return returnMessage;
        return null;
    }
}
