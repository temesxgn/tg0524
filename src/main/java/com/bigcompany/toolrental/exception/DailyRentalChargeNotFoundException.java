package com.bigcompany.toolrental.exception;

public class DailyRentalChargeNotFoundException extends ToolRentalException {
    public DailyRentalChargeNotFoundException(String msg) {
        super(msg);
    }

    public DailyRentalChargeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
