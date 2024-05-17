package com.bigcompany.toolrental.service;

import com.bigcompany.toolrental.model.Holiday;

import java.time.LocalDate;
import java.util.List;

public interface HolidayService {
    void insertHolidays(List<Holiday> holidays);

    void deleteBeforeYear(int year);

    List<Holiday> findHolidaysBetween(LocalDate startDate, LocalDate endDate);
}
