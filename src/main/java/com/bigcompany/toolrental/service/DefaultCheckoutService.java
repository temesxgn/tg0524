package com.bigcompany.toolrental.service;

import com.bigcompany.toolrental.exception.ToolRentalException;
import com.bigcompany.toolrental.model.*;
import com.bigcompany.toolrental.util.CalculationUtil;
import com.bigcompany.toolrental.util.RentalAgreementUtil;
import com.bigcompany.toolrental.validator.CheckoutValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultCheckoutService implements CheckoutService {

    private final ToolService toolService;
    private final RentalAgreementService rentalAgreementService;
    private final DailyRentalChargeService dailyRentalChargeService;
    private final HolidayService holidayService;

    /**
     * Process the checkout request.
     *
     * @param request the checkout request
     * @return returns the generated reference code
     * @throws ToolRentalException if any checkout related exceptions occur
     */
    @Transactional
    public RentalAgreement checkout(CheckoutRequest request) throws ToolRentalException {
        CheckoutValidator.validateCheckoutRequest(request);
        Tool tool = toolService.findByCode(request.getToolCode());
        LocalDate start = request.getCheckoutDate();
        LocalDate end = start.plusDays(request.getRentalLengthInDays());
        List<Holiday> holidays = holidayService.findHolidaysBetween(start, end);
        DailyRentalCharge charge = dailyRentalChargeService.findByToolTypeId(tool.getToolTypeId());
        RentalAgreementCalculation calculation = CalculationUtil.calculate(request, charge, holidays);
        RentalAgreement agreement = RentalAgreementUtil.buildRentalAgreement(request, tool, calculation);
        return rentalAgreementService.createRentalAgreement(agreement);
    }
}
