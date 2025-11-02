package com.example.usecase.person;

import java.util.Optional;

import com.example.domain.entity.Person;

public interface FindByNameInteractor {
    Optional<Person> find(String name);
}
