package com.bigcompany.toolrental.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Arrays;

@Getter
@ToString
@RequiredArgsConstructor
public enum ToolCode {
    Chainsaw("CHNS"),
    Ladder("LADW"),
    JackhammerDeWalt("JAKD"),
    JackhammerRidgid("JAKR");

    private final String name;

    public static ToolCode fromShortName(String shortName) {
        return Arrays.stream(values())
                .filter(code -> code.getName().equalsIgnoreCase(shortName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No constant with short name %s found", shortName)));
    }
}