package com.bigcompany.toolrental.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;

@UtilityClass
public class FormatUtil {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yy");
    private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance(Locale.US);
    private static final NumberFormat PERCENT_FORMATTER = NumberFormat.getPercentInstance();

    public String formatDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

    public String formatCurrency(BigDecimal amount) {
        return CURRENCY_FORMATTER.format(amount);
    }

    public String formatPercent(BigDecimal percent) {
        BigDecimal percentValue = percent.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        return PERCENT_FORMATTER.format(percentValue);
    }
}
