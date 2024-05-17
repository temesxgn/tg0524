package com.bigcompany.toolrental.exception;

public class InvalidCommandTypeException extends Exception {
    public InvalidCommandTypeException() {
        super();
    }

    public InvalidCommandTypeException(String msg) {
        super(msg);
    }

    public InvalidCommandTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
