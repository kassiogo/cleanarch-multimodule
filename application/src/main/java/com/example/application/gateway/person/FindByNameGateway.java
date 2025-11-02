package com.example.application.gateway.person;

import java.util.Optional;

import com.example.domain.entity.Person;

public interface FindByNameGateway {
    Optional<Person> find(String name);
}
