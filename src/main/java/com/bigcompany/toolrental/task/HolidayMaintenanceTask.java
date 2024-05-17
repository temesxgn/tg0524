package com.bigcompany.toolrental.task;

import com.bigcompany.toolrental.config.HolidayProperties;
import com.bigcompany.toolrental.mapper.HolidayMapper;
import com.bigcompany.toolrental.model.Holiday;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;

@Slf4j
@Component
@RequiredArgsConstructor
public class HolidayMaintenanceTask {

    private final HolidayMapper mapper;
    private final HolidayProperties properties;

    @Transactional
    @Scheduled(cron = "0 0 0 1 1 *", zone = "UTC")
    public void manageHolidays() {
        int currentYear = Year.now().getValue();
        boolean run = (currentYear - properties.getBaseYear()) % properties.getTaskYearsAhead() == 0;
        if (run) {
            log.info("Running holiday maintenance task for year: {}", currentYear);
            mapper.deleteBeforeYear(currentYear);
            for (int year = currentYear; year <= currentYear + properties.getTaskYearsAhead(); year++) {
                insertHolidaysForYear(year);
            }
        } else {
            log.info("Skipping holiday maintenance task for year: {}", currentYear);
        }
    }

    private void insertHolidaysForYear(int year) {
        try {
            // Independence Day
            LocalDate julyFourth = LocalDate.of(year, 7, 4);
            LocalDate observedJulyFourth = getObservedDate(julyFourth);
            mapper.insertHoliday(
                    Holiday.builder()
                            .name("Independence Day")
                            .date(julyFourth)
                            .observedDate(observedJulyFourth)
                            .year(year)
                            .build());

            // Labor Day
            LocalDate laborDay = LocalDate.of(year, 9, 1)
                    .with(TemporalAdjusters.firstInMonth(java.time.DayOfWeek.MONDAY));
            mapper.insertHoliday(
                    Holiday.builder()
                            .name("Labor Day")
                            .date(laborDay)
                            .observedDate(laborDay)
                            .year(year)
                            .build());

            log.info("Inserted holidays for year: {}", year);
        } catch (Exception e) {
            log.error("Error inserting holidays for year: {}", year, e);
        }
    }

    private LocalDate getObservedDate(LocalDate date) {
        switch (date.getDayOfWeek()) {
            case SATURDAY:
                return date.minusDays(1);
            case SUNDAY:
                return date.plusDays(1);
            default:
                return date;
        }
    }
}
