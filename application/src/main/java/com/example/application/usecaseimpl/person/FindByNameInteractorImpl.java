package com.example.application.usecaseimpl.person;

import java.util.Optional;

import com.example.application.gateway.person.FindByNameGateway;
import com.example.domain.entity.Person;
import com.example.usecase.person.FindByNameInteractor;

public class FindByNameInteractorImpl implements FindByNameInteractor {

    private final FindByNameGateway findByNameGateway;

    public FindByNameInteractorImpl(FindByNameGateway findByNameGateway) {
        this.findByNameGateway = findByNameGateway;
    }

    @Override
    public Optional<Person> execute(String name) {
        return findByNameGateway.find(name);
    }

}
