package com.bigcompany.toolrental.command;

import com.bigcompany.toolrental.exception.InvalidCommandTypeException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommandFactory {

    public Command getCommand(CommandFactoryInput input) throws InvalidCommandTypeException {
        switch (input.getCommandType()) {
            case DONE:
                return ExitCommand.builder().build();
            case CHECKOUT:
                return CheckoutCommand.builder()
                        .toolTypeService(input.getToolTypeService())
                        .checkoutService(input.getCheckoutService())
                        .brandService(input.getBrandService())
                        .build();
            case LIST:
                return ListItemsCommand.builder()
                        .toolService(input.getToolService())
                        .build();
            default:
                throw new InvalidCommandTypeException(String.format("No handler for command %s", input.getCommandType().name()));
        }
    }
}
