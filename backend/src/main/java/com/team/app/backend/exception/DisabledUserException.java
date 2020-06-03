package com.team.app.backend.exception;

public class DisabledUserException extends Exception {
    public DisabledUserException() {}

    public DisabledUserException(String message) {
        super(message);
    }

    public DisabledUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public DisabledUserException(Throwable cause) {
        super(cause);
    }

    public DisabledUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}