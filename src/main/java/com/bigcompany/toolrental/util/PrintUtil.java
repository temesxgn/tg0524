package com.bigcompany.toolrental.util;

import com.bigcompany.toolrental.model.Brand;
import com.bigcompany.toolrental.model.RentalAgreement;
import com.bigcompany.toolrental.model.ToolType;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;

@UtilityClass
public class PrintUtil {

    private static final String RENTAL_AGREEMENT_TEMPLATE =
            "Tool code: %s\n" +
                    "Tool type: %s\n" +
                    "Brand: %s\n" +
                    "Rental length: %d days\n" +
                    "Checkout date: %s\n" +
                    "Due date: %s\n" +
                    "Daily rental charge: %s\n" +
                    "Number of charged days: %d\n" +
                    "Pre-discount charge: %s\n" +
                    "Discount percent: %s\n" +
                    "Discount amount: %s\n" +
                    "Final charge: %s\n";

    public void println(String s) {
        System.out.println(s);
    }

    public void print(String s) {
        System.out.print(s);
    }

    public void printRentalAgreement(RentalAgreement agreement, Map<Long, ToolType> toolTypeMap, Map<Long, Brand> brandMap) {
        String formattedAgreement = String.format(RENTAL_AGREEMENT_TEMPLATE,
                agreement.getToolCode(),
                toolTypeMap.get(agreement.getToolTypeId()).getName(),
                brandMap.get(agreement.getBrandId()).getName(),
                agreement.getRentalLength(),
                FormatUtil.formatDate(agreement.getCheckoutDate().toLocalDate()),
                FormatUtil.formatDate(agreement.getDueDate().toLocalDate()),
                FormatUtil.formatCurrency(agreement.getDailyRentalCharge()),
                agreement.getNumOfChargedDays(),
                FormatUtil.formatCurrency(agreement.getPreDiscountCharge()),
                FormatUtil.formatPercent(agreement.getDiscountPercent()),
                FormatUtil.formatCurrency(agreement.getDiscountAmount()),
                FormatUtil.formatCurrency(agreement.getFinalCharge()));

        println(formattedAgreement);
    }
}
