package com.example.infrastructure.gateway.person;

import org.springframework.stereotype.Repository;

import com.example.application.gateway.person.CreatePersonGateway;
import com.example.domain.entity.Person;
import com.example.infrastructure.persistence.jpa.PersonJpaRepository;

import static java.util.Objects.requireNonNull;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CreatePersonGatewayImpl implements CreatePersonGateway {

    private final PersonJpaRepository personJpaRepository;
    private final com.example.infrastructure.mappers.PersonMapper personMapper;

    @Override
    public Person createPerson(Person person) {
        var entity = requireNonNull(personMapper.toEntity(person));
        var savedEntity = personJpaRepository.save(entity);
        return personMapper.toDomain(savedEntity);
    }

}
