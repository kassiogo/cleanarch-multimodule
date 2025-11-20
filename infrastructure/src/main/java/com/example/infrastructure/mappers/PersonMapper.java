package com.example.infrastructure.mappers;

import org.mapstruct.Mapper;

import com.example.domain.entity.Person;
import com.example.infrastructure.persistence.entity.PersonEntity;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    Person toDomain(PersonEntity entity);

    PersonEntity toEntity(Person person);
}
