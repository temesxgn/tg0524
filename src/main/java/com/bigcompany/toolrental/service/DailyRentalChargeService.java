package com.bigcompany.toolrental.service;

import com.bigcompany.toolrental.exception.DailyRentalChargeNotFoundException;
import com.bigcompany.toolrental.mapper.DailyRentalChargeMapper;
import com.bigcompany.toolrental.model.DailyRentalCharge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public interface DailyRentalChargeService {

    List<DailyRentalCharge> findAll();

    DailyRentalCharge findByToolTypeId(Long id) throws DailyRentalChargeNotFoundException;
}
