package com.bigcompany.toolrental.exception;

public class ReferenceNumberGenerationException extends ToolRentalException {
    public ReferenceNumberGenerationException(String msg) {
        super(msg);
    }

    public ReferenceNumberGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
