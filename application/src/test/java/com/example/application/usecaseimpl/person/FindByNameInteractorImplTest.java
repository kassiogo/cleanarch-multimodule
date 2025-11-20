package com.example.application.usecaseimpl.person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.application.gateway.person.FindByNameGateway;
import com.example.application.mock.MocksFactory;
import com.example.domain.entity.Person;
import com.example.usecase.person.FindByNameInteractor;

class FindByNameInteractorImplTest {

    @Test
    @DisplayName("Should return person when found by name")
    void shouldReturnPerson_whenFoundByName() {
        var person = MocksFactory.person();
        FindByNameGateway findByNameGateway = mock(FindByNameGateway.class);
        FindByNameInteractor findByNameInteractor = new FindByNameInteractorImpl(findByNameGateway);

        when(findByNameGateway.find(person.getName())).thenReturn(Optional.of(person));

        Optional<Person> result = findByNameInteractor.execute(person.getName());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(person);
        verify(findByNameGateway).find(person.getName());

    }

    @Test
    @DisplayName("Should return empty when not found by name")
    void shouldReturnEmpty_whenNotFoundByName() {
        FindByNameGateway findByNameGateway = mock(FindByNameGateway.class);
        FindByNameInteractor findByNameInteractor = new FindByNameInteractorImpl(findByNameGateway);

        when(findByNameGateway.find("any person name")).thenReturn(Optional.empty());

        Optional<Person> result = findByNameInteractor.execute("any person name");

        assertThat(result).isEmpty();
        verify(findByNameGateway, times(1)).find("any person name");
    }
}