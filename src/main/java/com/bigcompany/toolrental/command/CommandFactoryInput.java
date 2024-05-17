package com.bigcompany.toolrental.command;

import com.bigcompany.toolrental.service.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CommandFactoryInput {

    private final CommandType commandType;
    private final ToolService toolService;
    private final CheckoutService checkoutService;
    private final BrandService brandService;
    private final RentalAgreementService rentalAgreementService;
    private final ToolTypeService toolTypeService;

}
