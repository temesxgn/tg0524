package com.bigcompany.toolrental.util;

import com.bigcompany.toolrental.model.CheckoutRequest;
import com.bigcompany.toolrental.model.DailyRentalCharge;
import com.bigcompany.toolrental.model.Holiday;
import com.bigcompany.toolrental.model.RentalAgreementCalculation;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class CalculationUtil {
    public Integer calculateDaysCharged(DailyRentalCharge charge, LocalDate checkoutTime, Integer rentalLengthInDays, List<Holiday> holidays) {
        int totalChargedDays = 0;
        Map<LocalDate, Holiday> holidayMap = holidays.stream()
                .collect(Collectors.toMap(Holiday::getObservedDate, holiday -> holiday));

        for (int i = 1; i <= rentalLengthInDays; i++) {
            LocalDate futureDate = checkoutTime.plusDays(i);
            DayOfWeek dayOfWeek = futureDate.getDayOfWeek();
            boolean isHoliday = holidayMap.containsKey(futureDate);
            boolean isWeekend = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;

            if (isHoliday && charge.getChargeOnHoliday()) {
                totalChargedDays++;
            } else if (isWeekend && charge.getChargeOnWeekend()) {
                totalChargedDays++;
            } else if (!isHoliday && !isWeekend && charge.getChargeOnWeekday()) {
                totalChargedDays++;
            }
        }

        return totalChargedDays;
    }


    public RentalAgreementCalculation calculate(CheckoutRequest request, DailyRentalCharge charge, List<Holiday> holidays) {
        Integer chargedDays = CalculationUtil.calculateDaysCharged(charge, request.getCheckoutDate(), request.getRentalLengthInDays(), holidays);
        BigDecimal discountPercent = BigDecimal.valueOf(request.getDiscountPercent());
        BigDecimal total = charge.getChargeAmount().multiply(BigDecimal.valueOf(chargedDays));
        BigDecimal percent = BigDecimal.valueOf(request.getDiscountPercent());
        BigDecimal discountAmount = total.multiply(percent).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        Date dueDate = Date.valueOf(request.getCheckoutDate().plusDays(request.getRentalLengthInDays()));
        Date checkoutDate = Date.valueOf(request.getCheckoutDate());

        return RentalAgreementCalculation.builder()
                .dailyRate(charge.getChargeAmount())
                .chargedDays(chargedDays)
                .dueDate(dueDate)
                .checkoutDate(checkoutDate)
                .discountPercent(discountPercent)
                .discountAmount(discountAmount)
                .total(total)
                .finalCharge(total.subtract(discountAmount))
                .build();
    }
}
