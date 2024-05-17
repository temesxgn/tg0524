package com.bigcompany.toolrental.mapper;

import com.bigcompany.toolrental.model.RentalAgreement;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RentalAgreementMapper {

    @Results(id = "rentalAgreementResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "referenceNumber", column = "reference_number"),
            @Result(property = "toolCode", column = "tool_code"),
            @Result(property = "toolTypeId", column = "tool_type_id"),
            @Result(property = "brandId", column = "brand_id"),
            @Result(property = "rentalLength", column = "rental_length"),
            @Result(property = "checkoutDate", column = "checkout_date"),
            @Result(property = "dueDate", column = "due_date"),
            @Result(property = "dailyRentalCharge", column = "daily_rental_charge"),
            @Result(property = "numOfChargedDays", column = "num_of_charged_days"),
            @Result(property = "preDiscountCharge", column = "pre_discount_charge"),
            @Result(property = "discountPercent", column = "discount_percent"),
            @Result(property = "discountAmount", column = "discount_amount"),
            @Result(property = "finalCharge", column = "final_charge"),
            @Result(property = "createdAt", column = "created_at")
    })
    @Select("SELECT * FROM rental_agreement")
    List<RentalAgreement> findAll();

    @ResultMap("rentalAgreementResult")
    @Select("SELECT * FROM rental_agreement WHERE id = #{id}")
    Optional<RentalAgreement> findById(Long id);

    @ResultMap("rentalAgreementResult")
    @Select("SELECT * FROM rental_agreement WHERE reference_number = #{ref}")
    Optional<RentalAgreement> findByReferenceNumber(String ref);

    @Insert("INSERT INTO rental_agreement (reference_number, tool_code, tool_type_id, brand_id, rental_length, " +
            "checkout_date, due_date, daily_rental_charge, num_of_charged_days, pre_discount_charge, " +
            "discount_percent, discount_amount, final_charge) VALUES " +
            "(#{referenceNumber}, #{toolCode}, #{toolTypeId}, #{brandId}, #{rentalLength}, #{checkoutDate}, " +
            "#{dueDate}, #{dailyRentalCharge}, #{numOfChargedDays}, #{preDiscountCharge}, " +
            "#{discountPercent}, #{discountAmount}, #{finalCharge})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(RentalAgreement rentalAgreement);
}
