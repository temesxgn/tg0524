package com.bigcompany.toolrental.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@RequiredArgsConstructor
public class CheckoutRequest {
    private final String toolCode;
    private final Integer rentalLengthInDays;
    private final Integer discountPercent;
    private final LocalDate checkoutDate;
}
