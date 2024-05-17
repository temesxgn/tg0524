package com.bigcompany.toolrental.service;

import com.bigcompany.toolrental.model.Brand;
import com.bigcompany.toolrental.model.DailyRentalCharge;
import com.bigcompany.toolrental.model.Tool;
import com.bigcompany.toolrental.model.ToolType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CacheService {

    Optional<Map<Long, Tool>> getToolsFromCache(String key);
    Optional<Map<Long, ToolType>> getToolTypesFromCache(String key);
    Optional<Map<Long, Brand>> getBrandsFromCache(String key);

    Optional<Map<Long, DailyRentalCharge>> getDailyRentalChargesFromCache(String key);

    void clear();

    void put(String hashKey, Object data);

    void clearToolsCache(String hashKey);

    void clearDailyRentalChargesCache(String hashKey);
}
