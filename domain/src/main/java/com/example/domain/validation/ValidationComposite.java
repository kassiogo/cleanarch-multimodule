package com.example.domain.validation;

import java.util.List;

import com.example.domain.validation.validator.contract.Validator;

public class ValidationComposite implements Validator {
    private final List<Validator> validators;

    public ValidationComposite(List<Validator> validators) {
        this.validators = validators;
    }

    @Override
    public String validate() {
        for (Validator validator : validators) {
            String error = validator.validate();
            if (error != null) {
                return error;
            }
        }
        return null;
    }
}
