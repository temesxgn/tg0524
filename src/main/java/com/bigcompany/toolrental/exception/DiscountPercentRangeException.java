package com.bigcompany.toolrental.exception;

public class DiscountPercentRangeException extends ToolRentalException {
    public DiscountPercentRangeException(int value) {
        super(String.format("Discount percent must be between 0 and 100 but was %d", value));
    }
}
