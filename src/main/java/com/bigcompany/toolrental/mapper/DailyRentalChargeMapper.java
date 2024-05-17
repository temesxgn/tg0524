package com.bigcompany.toolrental.mapper;

import com.bigcompany.toolrental.model.DailyRentalCharge;
import com.bigcompany.toolrental.model.Tool;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface DailyRentalChargeMapper {

    @Select("SELECT id, tool_type_id, charge_amount, charge_on_weekday, charge_on_weekend, charge_on_holiday, created_at, updated_at FROM daily_rental_charge")
    @Results(id = "DailyRentalChargeMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "toolTypeId", column = "tool_type_id"),
            @Result(property = "chargeAmount", column = "charge_amount"),
            @Result(property = "chargeOnWeekday", column = "charge_on_weekday"),
            @Result(property = "chargeOnWeekend", column = "charge_on_weekend"),
            @Result(property = "chargeOnHoliday", column = "charge_on_holiday"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    List<DailyRentalCharge> findAll();

    @Select("SELECT id, tool_type_id, charge_amount, charge_on_weekday, charge_on_weekend, charge_on_holiday, created_at, updated_at FROM daily_rental_charge WHERE tool_type_id = #{id}")
    @ResultMap("DailyRentalChargeMap")
    Optional<DailyRentalCharge> findByToolTypeId(@Param("id") Long id);

}
