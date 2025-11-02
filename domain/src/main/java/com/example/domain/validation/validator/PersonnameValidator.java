package com.example.domain.validation.validator;

public class PersonnameValidator extends AbstractValidator {
    private final String returnMessage;

    public PersonnameValidator(Object fieldValue) {
        this.fieldValue = fieldValue;
        this.returnMessage = "Personname is invalid.";
    }

    @Override
    public String validate() {
        String username = (String) fieldValue;
        if (username.contains("@"))
            return returnMessage;
        return null;
    }
}
