package com.bigcompany.toolrental.service;

import com.bigcompany.toolrental.exception.DiscountPercentRangeException;
import com.bigcompany.toolrental.model.CheckoutRequest;
import com.bigcompany.toolrental.model.DailyRentalCharge;
import com.bigcompany.toolrental.model.RentalAgreement;
import com.bigcompany.toolrental.model.Tool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CheckoutServiceIT {

    @Autowired
    private ToolService toolService;

    @Autowired
    private DailyRentalChargeService dailyRentalChargeService;

    @Autowired
    private CheckoutService checkoutService;

    @Test
    void testScenario1() {
        CheckoutRequest req = createCheckoutRequest("JAKR", LocalDate.of(2015, 9, 3), 5, 101);
        assertThrowsExactly(DiscountPercentRangeException.class, () -> checkoutService.checkout(req));
    }

    @Test
    void testScenario2() {
        CheckoutRequest req = createCheckoutRequest("LADW", LocalDate.of(2020, 7, 2), 3, 10);
        RentalAgreement actual = checkoutService.checkout(req);
        assertNotNull(actual);
        RentalAgreement expected = RentalAgreement.builder()
                .id(actual.getId())
                .referenceNumber(actual.getReferenceNumber())
                .toolCode(req.getToolCode())
                .toolTypeId(2L)
                .brandId(2L)
                .rentalLength(req.getRentalLengthInDays())
                .checkoutDate(Date.valueOf(req.getCheckoutDate()))
                .dueDate(Date.valueOf(LocalDate.of(2020, 7, 5)))
                .dailyRentalCharge(BigDecimal.valueOf(1.99))
                .numOfChargedDays(2)
                .preDiscountCharge(BigDecimal.valueOf(3.98))
                .discountPercent(BigDecimal.valueOf(req.getDiscountPercent()))
                .discountAmount(BigDecimal.valueOf(0.40).setScale(2, RoundingMode.HALF_UP))
                .finalCharge(BigDecimal.valueOf(3.58))
                .createdAt(actual.getCreatedAt())
                .build();
        assertEquals(expected, actual);
    }

    @Test
    void testScenario3() {
        CheckoutRequest req = createCheckoutRequest("CHNS", LocalDate.of(2015, 7, 2), 5, 25);
        Tool tool = this.toolService.findByCode(req.getToolCode());
        assertNotNull(tool);
        DailyRentalCharge charge = this.dailyRentalChargeService.findByToolTypeId(tool.getToolTypeId());
        assertNotNull(charge);
        RentalAgreement actual = checkoutService.checkout(req);
        assertNotNull(actual);
        RentalAgreement expected = RentalAgreement.builder()
                .id(actual.getId())
                .referenceNumber(actual.getReferenceNumber())
                .toolCode(req.getToolCode())
                .toolTypeId(tool.getToolTypeId())
                .brandId(tool.getBrandId())
                .rentalLength(req.getRentalLengthInDays())
                .checkoutDate(Date.valueOf(req.getCheckoutDate()))
                .dueDate(Date.valueOf(LocalDate.of(2015, 7, 7)))
                .dailyRentalCharge(charge.getChargeAmount())
                .numOfChargedDays(3)
                .preDiscountCharge(BigDecimal.valueOf(4.47))
                .discountPercent(BigDecimal.valueOf(req.getDiscountPercent()))
                .discountAmount(BigDecimal.valueOf(1.12).setScale(2, RoundingMode.HALF_UP))
                .finalCharge(BigDecimal.valueOf(3.35))
                .createdAt(actual.getCreatedAt())
                .build();
        assertEquals(expected, actual);
    }

    @Test
    void testScenario4() {
        CheckoutRequest req = createCheckoutRequest("JAKD", LocalDate.of(2015, 9, 3), 6, 0);
        Tool tool = this.toolService.findByCode(req.getToolCode());
        assertNotNull(tool);
        DailyRentalCharge charge = this.dailyRentalChargeService.findByToolTypeId(tool.getToolTypeId());
        assertNotNull(charge);
        RentalAgreement actual = checkoutService.checkout(req);
        assertNotNull(actual);
        assertNotNull(actual);
        RentalAgreement expected = RentalAgreement.builder()
                .id(actual.getId())
                .referenceNumber(actual.getReferenceNumber())
                .toolCode(req.getToolCode())
                .toolTypeId(tool.getToolTypeId())
                .brandId(tool.getBrandId())
                .rentalLength(req.getRentalLengthInDays())
                .checkoutDate(Date.valueOf(req.getCheckoutDate()))
                .dueDate(Date.valueOf(LocalDate.of(2015, 9, 9)))
                .dailyRentalCharge(charge.getChargeAmount())
                .numOfChargedDays(3)
                .preDiscountCharge(BigDecimal.valueOf(8.97))
                .discountPercent(BigDecimal.valueOf(req.getDiscountPercent()))
                .discountAmount(BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP))
                .finalCharge(BigDecimal.valueOf(8.97))
                .createdAt(actual.getCreatedAt())
                .build();
        assertEquals(expected, actual);
    }

    @Test
    void testScenario5() {
        CheckoutRequest req = createCheckoutRequest("JAKR", LocalDate.of(2015, 7, 2), 9, 0);
        Tool tool = this.toolService.findByCode(req.getToolCode());
        assertNotNull(tool);
        DailyRentalCharge charge = this.dailyRentalChargeService.findByToolTypeId(tool.getToolTypeId());
        assertNotNull(charge);
        RentalAgreement actual = checkoutService.checkout(req);
        assertNotNull(actual);
        RentalAgreement expected = RentalAgreement.builder()
                .id(actual.getId())
                .referenceNumber(actual.getReferenceNumber())
                .toolCode(req.getToolCode())
                .toolTypeId(tool.getToolTypeId())
                .brandId(tool.getBrandId())
                .rentalLength(req.getRentalLengthInDays())
                .checkoutDate(Date.valueOf(req.getCheckoutDate()))
                .dueDate(Date.valueOf(LocalDate.of(2015, 7, 11)))
                .dailyRentalCharge(charge.getChargeAmount())
                .numOfChargedDays(5)
                .preDiscountCharge(BigDecimal.valueOf(14.95))
                .discountPercent(BigDecimal.valueOf(req.getDiscountPercent()))
                .discountAmount(BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP))
                .finalCharge(BigDecimal.valueOf(14.95))
                .createdAt(actual.getCreatedAt())
                .build();
        assertEquals(expected, actual);
    }

    @Test
    void testScenario6() {
        CheckoutRequest req = createCheckoutRequest("JAKR", LocalDate.of(2020, 7, 2), 4, 50);
        Tool tool = this.toolService.findByCode(req.getToolCode());
        assertNotNull(tool);
        DailyRentalCharge charge = this.dailyRentalChargeService.findByToolTypeId(tool.getToolTypeId());
        assertNotNull(charge);
        RentalAgreement actual = checkoutService.checkout(req);
        assertNotNull(actual);
        RentalAgreement expected = RentalAgreement.builder()
                .id(actual.getId())
                .referenceNumber(actual.getReferenceNumber())
                .toolCode(req.getToolCode())
                .toolTypeId(tool.getToolTypeId())
                .brandId(tool.getBrandId())
                .rentalLength(req.getRentalLengthInDays())
                .checkoutDate(Date.valueOf(req.getCheckoutDate()))
                .dueDate(Date.valueOf(LocalDate.of(2020, 7, 6)))
                .dailyRentalCharge(charge.getChargeAmount())
                .numOfChargedDays(1)
                .preDiscountCharge(BigDecimal.valueOf(2.99))
                .discountPercent(BigDecimal.valueOf(req.getDiscountPercent()))
                .discountAmount(BigDecimal.valueOf(1.50).setScale(2, RoundingMode.HALF_UP))
                .finalCharge(BigDecimal.valueOf(1.49))
                .createdAt(actual.getCreatedAt())
                .build();
        assertEquals(expected, actual);
    }

    private CheckoutRequest createCheckoutRequest(String toolCode, LocalDate checkoutDate, int rentalLength, int discount) {
        return CheckoutRequest.builder()
                .toolCode(toolCode)
                .rentalLengthInDays(rentalLength)
                .discountPercent(discount)
                .checkoutDate(checkoutDate)
                .build();
    }
}
