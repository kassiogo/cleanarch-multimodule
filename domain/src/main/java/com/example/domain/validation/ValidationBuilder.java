package com.example.domain.validation;

import java.util.ArrayList;
import java.util.List;

import com.example.domain.validation.validator.EmailValidator;
import com.example.domain.validation.validator.PersonnameValidator;
import com.example.domain.validation.validator.RequiredFieldValidator;
import com.example.domain.validation.validator.contract.Validator;

public class ValidationBuilder {
    private final List<Validator> validators = new ArrayList<>();
    private final String fieldName;
    private final Object fieldValue;

    private ValidationBuilder(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public static ValidationBuilder of(String fieldName, Object fieldValue) {
        return new ValidationBuilder(fieldName, fieldValue);
    }

    public ValidationBuilder required() {
        this.validators.add(new RequiredFieldValidator(this.fieldName, this.fieldValue));
        return this;
    }

    public ValidationBuilder personname() {
        this.validators.add(new PersonnameValidator(this.fieldValue));
        return this;
    }

    public ValidationBuilder email() {
        this.validators.add(new EmailValidator(this.fieldValue));
        return this;
    }

    public List<Validator> build() {
        return this.validators;
    }

}
