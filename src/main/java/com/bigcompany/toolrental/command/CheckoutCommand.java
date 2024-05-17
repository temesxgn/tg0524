package com.bigcompany.toolrental.command;

import com.bigcompany.toolrental.model.*;
import com.bigcompany.toolrental.service.BrandService;
import com.bigcompany.toolrental.service.CheckoutService;
import com.bigcompany.toolrental.service.ToolTypeService;
import com.bigcompany.toolrental.util.PrintUtil;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Builder
@RequiredArgsConstructor
public class CheckoutCommand extends BaseCommand {

    private final CheckoutService checkoutService;
    private final ToolTypeService toolTypeService;
    private final BrandService brandService;

    @Override
    public void execute() {
        PrintUtil.println("Select item to rent");
        PrintUtil.print("Tool Code: ");
        String toolCode = scanner.next();
        PrintUtil.println("Rental Day Count: ");
        Integer rentalDayCount = scanner.nextInt();
        PrintUtil.println("Discount Percent: ");
        Integer discountPercent = scanner.nextInt();
        PrintUtil.println("Check out date (mm/dd/yy): ");
        String checkoutDate = scanner.next();

        CheckoutRequest req = CheckoutRequest.builder()
                .toolCode(toolCode)
                .rentalLengthInDays(rentalDayCount)
                .discountPercent(discountPercent)
                .checkoutDate(LocalDate.parse(checkoutDate, DateTimeFormatter.ofPattern("MM/dd/yy")))
                .build();

        RentalAgreement agreement = checkoutService.checkout(req);
        Map<Long, ToolType> toolTypes = toolTypeService.findAll()
                .stream()
                .collect(Collectors.toMap(
                        ToolType::getId,
                        toolType -> toolType
                ));

        Map<Long, Brand> brands = brandService.findAll()
                .stream()
                .collect(Collectors.toMap(
                        Brand::getId,
                        brand -> brand
                ));

        PrintUtil.printRentalAgreement(agreement, toolTypes, brands);
    }
}
