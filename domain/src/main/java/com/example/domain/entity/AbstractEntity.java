package com.example.domain.entity;

import java.util.List;

import com.example.domain.validation.ValidationComposite;
import com.example.domain.validation.validator.contract.Validator;

public abstract class AbstractEntity {
    protected List<Validator> buildValidators() {
        return List.of();
    }

    protected String validate() {
        List<Validator> validators = this.buildValidators();
        return new ValidationComposite(validators).validate();
    }
}
