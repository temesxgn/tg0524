package com.bigcompany.toolrental.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@Builder
@RequiredArgsConstructor
public class RentalAgreementCalculation {
    private final BigDecimal dailyRate;
    private final int chargedDays;
    private final BigDecimal total;
    private final BigDecimal discountPercent;
    private final BigDecimal discountAmount;
    private final BigDecimal finalCharge;
    private final Date dueDate;
    private final Date checkoutDate;
}
