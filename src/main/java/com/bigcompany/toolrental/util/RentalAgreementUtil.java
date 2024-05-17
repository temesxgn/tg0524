package com.bigcompany.toolrental.util;

import com.bigcompany.toolrental.model.CheckoutRequest;
import com.bigcompany.toolrental.model.RentalAgreement;
import com.bigcompany.toolrental.model.RentalAgreementCalculation;
import com.bigcompany.toolrental.model.Tool;
import lombok.experimental.UtilityClass;

import java.sql.Timestamp;

@UtilityClass
public class RentalAgreementUtil {

    public RentalAgreement buildRentalAgreement(CheckoutRequest request, Tool tool, RentalAgreementCalculation calculation) {
        return RentalAgreement.builder()
                .toolCode(request.getToolCode())
                .toolTypeId(tool.getToolTypeId())
                .brandId(tool.getBrandId())
                .rentalLength(request.getRentalLengthInDays())
                .checkoutDate(calculation.getCheckoutDate())
                .dueDate(calculation.getDueDate())
                .dailyRentalCharge(calculation.getDailyRate())
                .numOfChargedDays(calculation.getChargedDays())
                .preDiscountCharge(calculation.getTotal())
                .discountPercent(calculation.getDiscountPercent())
                .discountAmount(calculation.getDiscountAmount())
                .finalCharge(calculation.getFinalCharge())
                .createdAt(Timestamp.valueOf(request.getCheckoutDate().atStartOfDay()))
                .build();
    }
}
