package com.example.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.domain.entity.Person;
import com.example.infrastructure.entrypoint.dto.person.CreatePersonRequest;
import com.example.infrastructure.entrypoint.dto.person.PersonResponse;
import com.example.infrastructure.persistence.entity.PersonEntity;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    Person toDomain(PersonEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    Person toDomain(CreatePersonRequest request);

    PersonResponse toResponse(Person person);

    PersonEntity toEntity(Person person);
}
