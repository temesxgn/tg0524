package com.bigcompany.toolrental.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Setter
@Getter
@Validated
@Configuration
@ConfigurationProperties(prefix = "cache")
public class CacheProperties {

    private Duration defaultTtl = Duration.ofHours(2);
    private Duration toolTtl = Duration.ofHours(1);

}

