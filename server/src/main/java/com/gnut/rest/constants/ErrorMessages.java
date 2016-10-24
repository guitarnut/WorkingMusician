package com.gnut.rest.constants;

/**
 * Created by guitarnut on 10/24/16.
 */
public enum ErrorMessages {
    INVALID_PARAMETERS("Invalid or missing parameter values");

    String value;

    ErrorMessages(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
