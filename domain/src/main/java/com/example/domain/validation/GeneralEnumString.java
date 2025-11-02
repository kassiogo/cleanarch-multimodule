package com.example.domain.validation;

public enum GeneralEnumString {
    EMAIL_REGEX_EXPRESSION(
            "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    private final String value;

    GeneralEnumString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
