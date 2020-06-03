package com.team.app.backend.exception;

public class NotActivatedUserException extends Exception {
    public NotActivatedUserException() {}

    public NotActivatedUserException(String message) {
        super(message);
    }

    public NotActivatedUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotActivatedUserException(Throwable cause) {
        super(cause);
    }

    public NotActivatedUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}