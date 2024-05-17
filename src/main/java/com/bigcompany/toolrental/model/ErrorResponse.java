package com.bigcompany.toolrental.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String errorCode;
    private String message;
    private String detail;
}
