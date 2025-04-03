package com.henrique.banc.authorization.domain;

public record Authorization(
        String message
) {
    public boolean isAuthorized() {
        return message.equals("Authorized");
    }
}
