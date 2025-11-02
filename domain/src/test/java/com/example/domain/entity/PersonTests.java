package com.example.domain.entity;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.domain.exception.DomainException;
import com.github.javafaker.Faker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class PersonTests {
    private final Faker faker = new Faker();

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {
        @Test
        @DisplayName("Should build person with all arguments constructor")
        void shouldBuildPerson_WithAllArgumentsConstructor() {
            Long id = faker.number().randomNumber();
            String name = faker.name().fullName();
            String email = faker.internet().emailAddress();
            Boolean active = faker.bool().bool();

            Person person = new Person(id, name, email, active);

            assertThat(person.getId()).isEqualTo(id);
            assertThat(person.getName()).isEqualTo(name);
            assertThat(person.getEmail()).isEqualTo(email);
            assertThat(person.getActive()).isEqualTo(active);
        }

        @Test
        @DisplayName("Should build person with required arguments constructor")
        void shouldBuildPerson_WithRequiredArgumentsConstructor() {
            String name = faker.name().fullName();
            String email = faker.internet().emailAddress();

            Person person = new Person(name, email);

            assertThat(person.getId()).isNull();
            assertThat(person.getName()).isEqualTo(name);
            assertThat(person.getEmail()).isEqualTo(email);
            assertThat(person.getActive()).isNull();
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("Should throw DomainException when trying to build person with name empty or null")
        void shouldThrowDomainException_WhenTryingToBuildPersonWithNameEmptyOrNull(String name) {
            Throwable exception = catchThrowable(() -> new Person(
                    null,
                    name,
                    faker.internet().emailAddress(),
                    true));

            assertThat(exception).isInstanceOf(DomainException.class);
            assertThat(exception.getMessage()).isEqualTo("Name is required.");
        }

        @ParameterizedTest
        @ValueSource(strings = { "Invalid Name 123", "Another@Invalid" })
        @DisplayName("Should throw DomainException when trying to build person with invalid name format")
        void shouldThrowDomainException_WhenTryingToBuildPersonWithInvalidNameFormat(String name) {
            Throwable exception = catchThrowable(() -> new Person(
                    name,
                    faker.internet().emailAddress()));

            assertThat(exception).isInstanceOf(DomainException.class);
            assertThat(exception.getMessage()).isEqualTo("Name must contain only letters and spaces.");
        }

        @ParameterizedTest
        @ValueSource(strings = { "invalid-email", "invalid.com", "@invalid.com" })
        @DisplayName("Should throw DomainException when trying to build person with invalid email")
        void shouldThrowDomainException_WhenTryingToBuildPersonWithInvalidEmail(String email) {
            Throwable exception = catchThrowable(() -> new Person(
                    faker.name().fullName(),
                    email));

            assertThat(exception).isInstanceOf(DomainException.class);
            assertThat(exception.getMessage()).isEqualTo("E-mail is invalid.");
        }
    }

    @Nested
    @DisplayName("Setter Tests")
    class SetterTests {
        private Person person;

        @org.junit.jupiter.api.BeforeEach
        void setUp() {
            person = new Person(faker.name().fullName(), faker.internet().emailAddress());
        }

        @Test
        @DisplayName("Should set name successfully")
        void shouldSetNameSuccessfully() {
            String newName = faker.name().fullName();
            person.setName(newName);
            assertThat(person.getName()).isEqualTo(newName);
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("Should throw DomainException when setting name to null or empty")
        void shouldThrowDomainException_WhenSettingNameToNullOrEmpty(String name) {
            Throwable exception = catchThrowable(() -> person.setName(name));

            assertThat(exception).isInstanceOf(DomainException.class);
            assertThat(exception.getMessage()).isEqualTo("Name is required.");
        }

        @Test
        @DisplayName("Should set email successfully")
        void shouldSetEmailSuccessfully() {
            String newEmail = faker.internet().emailAddress();
            person.setEmail(newEmail);
            assertThat(person.getEmail()).isEqualTo(newEmail);
        }

        @ParameterizedTest
        @ValueSource(strings = { "invalid-email", "invalid.com", "@invalid.com" })
        @DisplayName("Should throw DomainException when setting an invalid email")
        void shouldThrowDomainException_WhenSettingInvalidEmail(String email) {
            Throwable exception = catchThrowable(() -> person.setEmail(email));

            assertThat(exception).isInstanceOf(DomainException.class);
            assertThat(exception.getMessage()).isEqualTo("E-mail is invalid.");
        }
    }

    @Nested
    @DisplayName("Equals and HashCode Tests")
    class EqualsAndHashCodeTests {

        @Test
        @DisplayName("Should be equal if ids are the same")
        void shouldBeEqualIfIdsAreTheSame() {
            Long id = faker.number().randomNumber();
            Person person1 = new Person(id, "John Doe", "john.doe@example.com", true);
            Person person2 = new Person(id, "Jane Doe", "jane.doe@example.com", false);

            assertThat(person1).isEqualTo(person2);
            assertThat(person1.hashCode()).isEqualTo(person2.hashCode());
        }

        @Test
        @DisplayName("Should not be equal if ids are different")
        void shouldNotBeEqualIfIdsAreDifferent() {
            Person person1 = new Person(1L, "John Doe", "john.doe@example.com", true);
            Person person2 = new Person(2L, "John Doe", "john.doe@example.com", true);

            assertThat(person1).isNotEqualTo(person2);
        }

        @Test
        @DisplayName("Should not be equal if one id is null")
        void shouldNotBeEqualIfOneIdIsNull() {
            Person person1 = new Person(1L, "John Doe", "john.doe@example.com", true);
            Person person2 = new Person(null, "John Doe", "john.doe@example.com", true);

            assertThat(person1).isNotEqualTo(person2);
            assertThat(person2).isNotEqualTo(person1);
        }
    }
}
