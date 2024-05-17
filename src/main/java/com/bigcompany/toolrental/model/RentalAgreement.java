package com.bigcompany.toolrental.model;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "createdAt"})
public class RentalAgreement {
    private Long id;
    private String referenceNumber;
    private String toolCode;
    private Long toolTypeId;
    private Long brandId;
    private int rentalLength;
    private Date checkoutDate;
    private Date dueDate;
    private BigDecimal dailyRentalCharge;
    private int numOfChargedDays;
    private BigDecimal preDiscountCharge;
    private BigDecimal discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;
    private Timestamp createdAt;
}
