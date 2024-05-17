package com.bigcompany.toolrental.exception;

import lombok.Builder;

public class ToolNotFoundException extends ToolRentalException {
    public ToolNotFoundException(String msg) {
        super(msg);
    }
}
