package com.example.application.usecaseimpl.person;

import com.example.application.gateway.person.CreatePersonGateway;
import com.example.domain.entity.Person;
import com.example.domain.exception.ConflictException;
import com.example.usecase.person.CreatePersonInteractor;
import com.example.usecase.person.FindByNameInteractor;

public class CreatePersonInteractorImpl implements CreatePersonInteractor {
    private final CreatePersonGateway createPersonGateway;
    private final FindByNameInteractor findByNameInteractor;

    public CreatePersonInteractorImpl(
            CreatePersonGateway createPersonGateway,
            FindByNameInteractor findByNameInteractor) {
        this.createPersonGateway = createPersonGateway;
        this.findByNameInteractor = findByNameInteractor;
    }

    @Override
    public Person execute(Person person) {
        findByNameInteractor.execute(person.getName())
                .ifPresent(p -> {
                    throw new ConflictException("Person already exists");
                });

        person.setActive(true);
        return createPersonGateway.createPerson(person);
    }

}
