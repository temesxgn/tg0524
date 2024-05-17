package com.bigcompany.toolrental.model;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyRentalCharge {
    private Long id;
    private Long toolTypeId;
    private BigDecimal chargeAmount;
    private Boolean chargeOnWeekday;
    private Boolean chargeOnWeekend;
    private Boolean chargeOnHoliday;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
