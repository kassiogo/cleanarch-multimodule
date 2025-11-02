package com.example.application.usecaseimpl.person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.example.application.gateway.person.FindByNameGateway;
import com.example.domain.entity.Person;
import com.github.javafaker.Faker;

class FindByNameInteractorImplTest {
    @Test
    @DisplayName("Should return person when found by name")
    void shouldReturnPerson_whenFoundByName() {
        // Given
        final var person = new Person(Faker.instance().name().fullName(), Faker.instance().number().age());
        FindByUsernameGateway findByUsernameGateway = mock(FindByNameGateway.class);
        when(findByNameGateway.find(person.getName())).thenReturn(Optional.of(person));

        // When
        final var actual = findByNameInteractor.find(person.getName());

        // Then
        assertThat(actual).isPresent();
        assertThat(actual.get()).isEqualTo(person);
        verify(findByNameGateway).find(person.getName());

    }
}