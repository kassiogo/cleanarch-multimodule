package com.example.application.mock;

import com.example.domain.entity.Person;

public class MocksFactory {
    private static final Long ID = 1L;
    private static final String EMAIL = "user@mail.com";
    private static final String NAME = "user";
    private static final Boolean ACTIVE = true;

    private MocksFactory() {
    }

    public static Person person() {
        return new Person(ID, NAME, EMAIL, ACTIVE);
    }

    public static Person personWithNoId() {
        return new Person(NAME, EMAIL);
    }

}
