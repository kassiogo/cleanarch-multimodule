package com.example.domain.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.domain.exception.DomainException;
import com.example.domain.validation.ValidationBuilder;
import com.example.domain.validation.validator.contract.Validator;

public class Person extends AbstractEntity {

    private Long id;
    private String name;
    private Boolean active;
    private String email;

    public Person(Long id, String name, String email, Boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.active = active;

        String error = this.validate();
        if (error != null)
            throw new DomainException(error);
    }

    public Person(String name, String email) {
        this.name = name;
        this.email = email;

        String error = this.validate();
        if (error != null)
            throw new DomainException(error);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        String error = this.validate();
        if (error != null)
            throw new DomainException(error);
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        String error = this.validate();
        if (error != null)
            throw new DomainException(error);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    protected List<Validator> buildValidators() {
        List<Validator> validators = new ArrayList<>();
        validators.addAll(ValidationBuilder.of("Name", this.name)
                .required()
                .personname()
                .build());
        validators.addAll(ValidationBuilder.of("E-mail", this.email)
                .email()
                .build());
        return validators;
    }
}
