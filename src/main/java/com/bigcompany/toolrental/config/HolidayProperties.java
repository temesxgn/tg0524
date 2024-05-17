package com.bigcompany.toolrental.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@Validated
@Configuration
@ConfigurationProperties(prefix = "holidays")
public class HolidayProperties {
    private int taskYearsAhead = 3;
    private int baseYear = 2024;
}
