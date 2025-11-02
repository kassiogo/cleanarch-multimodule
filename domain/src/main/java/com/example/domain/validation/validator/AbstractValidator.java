package com.example.domain.validation.validator;

import com.example.domain.validation.validator.contract.Validator;

public abstract class AbstractValidator implements Validator {
    protected String fieldName;
    protected Object fieldValue;

    @Override
    public String validate() {
        return null;
    }
}
