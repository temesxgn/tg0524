package com.bigcompany.toolrental;

import com.bigcompany.toolrental.command.Command;
import com.bigcompany.toolrental.command.CommandFactory;
import com.bigcompany.toolrental.command.CommandFactoryInput;
import com.bigcompany.toolrental.command.CommandType;
import com.bigcompany.toolrental.exception.InvalidCommandTypeException;
import com.bigcompany.toolrental.exception.ToolRentalException;
import com.bigcompany.toolrental.service.*;
import com.bigcompany.toolrental.util.PrintUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsoleReader {

    private final CheckoutService checkoutService;
    private final RentalAgreementService rentalAgreementService;
    private final ToolTypeService toolTypeService;
    private final BrandService brandService;
    private final ToolService toolService;

    public void readInput() {
        Scanner scanner = new Scanner(System.in);
        PrintUtil.print("Hello, welcome to Tool Rentals!");

        while (true) {
            PrintUtil.println("Enter [L]ist, [D]one, [C]heckout");
            try {
                CommandType commandType = CommandType.findByCode(scanner.nextLine());
                Command command = CommandFactory.getCommand(
                        CommandFactoryInput
                                .builder()
                                .commandType(commandType)
                                .toolService(toolService)
                                .checkoutService(checkoutService)
                                .brandService(brandService)
                                .rentalAgreementService(rentalAgreementService)
                                .toolTypeService(toolTypeService)
                                .build()
                );
                command.execute();
            } catch (InvalidCommandTypeException e) {
                PrintUtil.println("Invalid input! Please select from the options above.");
            } catch (ToolRentalException e) {
                System.out.println(e.getMessage());
            }
        }
//        try {
//            CheckoutRequest req = CheckoutRequest.builder()
//                    .toolCode("JAKR")
//                    .rentalLengthInDays(9)
//                    .discountPercent(0)
//                    .checkoutTime(LocalDateTime.of(2015, 7, 2, 0, 0))
//                    .build();
//            String refNum = checkoutService.checkout(req);
//            Map<Long, ToolType> toolTypes = toolTypeService.findAll()
//                    .stream()
//                    .collect(Collectors.toMap(
//                            ToolType::getId,
//                            toolType -> toolType
//                    ));
//
//            Map<Long, Brand> brands = brandService.findAll()
//                    .stream()
//                    .collect(Collectors.toMap(
//                            Brand::getId,
//                            brand -> brand
//                    ));
//
//            var agreement = rentalAgreementService.findByReferenceNumber(refNum);
//            PrintUtil.printRentalAgreement(agreement, toolTypes, brands);
//        } catch (ToolRentalException e) {
//            System.out.println(e.getMessage());
//        }
    }
}

