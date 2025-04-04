package com.henrique.banc.authorization.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Authorization(String message) {

    @JsonCreator
    public Authorization(@JsonProperty("authorized") boolean authorized) {
        this(authorized ? "authorized" : "not authorized");
    }

    public boolean isAuthorized() {
        return "authorized".equals(message);
    }
}
