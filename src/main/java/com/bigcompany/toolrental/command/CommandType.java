package com.bigcompany.toolrental.command;

import com.bigcompany.toolrental.exception.InvalidCommandTypeException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Arrays;

@Getter
@ToString
@RequiredArgsConstructor
public enum CommandType {

    DONE("D"),
    LIST("L"),
    CHECKOUT("C"),;

    private final String code;

    public static CommandType findByCode(String c) throws InvalidCommandTypeException {
        return Arrays.stream(values())
                .filter(code -> code.getCode().equalsIgnoreCase(c))
                .findFirst()
                .orElseThrow(() -> new InvalidCommandTypeException(String.format("No command with code name %s found", c)));
    }
}
