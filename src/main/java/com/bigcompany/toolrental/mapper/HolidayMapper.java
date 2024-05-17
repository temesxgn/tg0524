package com.bigcompany.toolrental.mapper;

import com.bigcompany.toolrental.model.Holiday;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface HolidayMapper {

    @Insert("INSERT INTO holiday (name, date, observed_date, year) VALUES (#{holiday.name}, #{holiday.date}, #{holiday.observedDate}, #{holiday.year})")
    void insertHoliday(@Param("holiday") Holiday holiday);

    @Delete("DELETE FROM holiday WHERE year < #{year}")
    void deleteBeforeYear(@Param("year") int year);

    @Select("SELECT id, name, date, observed_date, year, created_at, updated_at FROM holiday WHERE observed_date BETWEEN #{startDate} AND #{endDate}")
    @Results(id = "holidayMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "date", column = "date"),
            @Result(property = "observedDate", column = "observed_date"),
            @Result(property = "year", column = "year"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    List<Holiday> findHolidaysBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
