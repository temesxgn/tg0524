package com.bigcompany.toolrental.service;

import com.bigcompany.toolrental.exception.DailyRentalChargeNotFoundException;
import com.bigcompany.toolrental.mapper.DailyRentalChargeMapper;
import com.bigcompany.toolrental.model.Brand;
import com.bigcompany.toolrental.model.DailyRentalCharge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultDailyRentalChargeService implements DailyRentalChargeService {

    private static final String FIND_ALL_CACHE_KEY = "findAllDailyRentalCharges";

    private final DailyRentalChargeMapper mapper;
    private final CacheService cacheService;

    @Override
    public List<DailyRentalCharge> findAll() {
        return cacheService.getDailyRentalChargesFromCache(FIND_ALL_CACHE_KEY)
                .map(Map::values)
                .map(List::copyOf)
                .orElseGet(() -> {
                    List<DailyRentalCharge> dailyRentalCharges = mapper.findAll();
                    if (!dailyRentalCharges.isEmpty()) {
                        Map<Long, DailyRentalCharge> chargeMap = dailyRentalCharges.stream()
                                .collect(Collectors.toMap(DailyRentalCharge::getId, charge -> charge));
                        cacheService.put(FIND_ALL_CACHE_KEY, chargeMap);
                    }
                    return dailyRentalCharges;
                });
    }

    @Override
    public DailyRentalCharge findByToolTypeId(Long id) throws DailyRentalChargeNotFoundException {
        return cacheService.getDailyRentalChargesFromCache(FIND_ALL_CACHE_KEY)
                .map(chargeMap -> chargeMap.get(id))
                .or(() -> mapper.findByToolTypeId(id))
                .orElseThrow(() -> new DailyRentalChargeNotFoundException(String.format("Daily rental charge not found for tool type ID: %d", id)));
    }
}
