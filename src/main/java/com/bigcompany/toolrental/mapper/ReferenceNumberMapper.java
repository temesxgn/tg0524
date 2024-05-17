package com.bigcompany.toolrental.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ReferenceNumberMapper {

    @Select("SELECT last_number FROM reference_number WHERE date = #{date}")
    Integer getLastNumberForDate(@Param("date") String date);

    @Insert("INSERT INTO reference_number (date, last_number) VALUES (#{date}, #{lastNumber})")
    void insertNewDate(@Param("date") String date, @Param("lastNumber") int lastNumber);

    @Update("UPDATE reference_number SET last_number = #{lastNumber} WHERE date = #{date}")
    void updateLastNumberForDate(@Param("date") String date, @Param("lastNumber") int lastNumber);
}