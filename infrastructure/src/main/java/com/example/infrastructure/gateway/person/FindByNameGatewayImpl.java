package com.example.infrastructure.gateway.person;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.application.gateway.person.FindByNameGateway;
import com.example.domain.entity.Person;
import com.example.infrastructure.mappers.PersonMapper;
import com.example.infrastructure.persistence.jpa.PersonJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FindByNameGatewayImpl implements FindByNameGateway {

    private final PersonJpaRepository personJpaRepository;
    private final PersonMapper personMapper;

    @Override
    public Optional<Person> find(String name) {
        var entity = personJpaRepository.findByName(name);
        return entity.map(personMapper::toDomain);
    }
}
