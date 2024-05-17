package com.bigcompany.toolrental.exception;

public class RentalDayCountException extends ToolRentalException {
    public RentalDayCountException() {
        super("Rental day count must be 1 or greater.");
    }
}
