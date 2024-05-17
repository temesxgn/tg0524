package com.bigcompany.toolrental.exception;

public class CreateRentalAgreementException extends ToolRentalException {
    public CreateRentalAgreementException(String message) {
        super(message);
    }

    public CreateRentalAgreementException(String message, Throwable cause) {
        super(message, cause);
    }
}
