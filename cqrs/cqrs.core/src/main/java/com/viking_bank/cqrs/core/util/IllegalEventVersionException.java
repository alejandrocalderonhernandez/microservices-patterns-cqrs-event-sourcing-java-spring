package com.viking_bank.cqrs.core.util;

public class IllegalEventVersionException extends RuntimeException {

    public IllegalEventVersionException(Integer version) {
        super(String.format(message, version));
    }

    private static final String message = "the version %d not exist";
}
