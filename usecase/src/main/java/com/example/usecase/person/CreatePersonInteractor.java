package com.example.usecase.person;

import com.example.domain.entity.Person;

public interface CreatePersonInteractor {
    Person execute(Person person);
}
