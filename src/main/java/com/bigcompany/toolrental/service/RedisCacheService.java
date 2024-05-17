package com.bigcompany.toolrental.service;

import com.bigcompany.toolrental.constants.CacheConstants;
import com.bigcompany.toolrental.model.Brand;
import com.bigcompany.toolrental.model.DailyRentalCharge;
import com.bigcompany.toolrental.model.Tool;
import com.bigcompany.toolrental.model.ToolType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisCacheService implements CacheService {

    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, Object> hashOperations;

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void clear() {
        redisTemplate.delete(CacheConstants.TOOLS_CACHE);
        redisTemplate.delete(CacheConstants.DAILY_RENTAL_CHARGES_CACHE);
    }

    @Override
    public void put(String hashKey, Object data) {
        hashOperations.put(CacheConstants.TOOLS_CACHE, hashKey, data);
    }

    @Override
    public Optional<Map<Long, Tool>> getToolsFromCache(String key) {
        return Optional.ofNullable((Map<Long, Tool>) hashOperations.get(CacheConstants.TOOLS_CACHE, key));
    }

    @Override
    public Optional<Map<Long, ToolType>> getToolTypesFromCache(String key) {
        return Optional.ofNullable((Map<Long, ToolType>) hashOperations.get(CacheConstants.TOOL_TYPES_CACHE, key));
    }

    @Override
    public Optional<Map<Long, Brand>> getBrandsFromCache(String key) {
        return Optional.ofNullable((Map<Long, Brand>) hashOperations.get(CacheConstants.BRANDS_CACHE, key));
    }


    @Override
    public Optional<Map<Long, DailyRentalCharge>> getDailyRentalChargesFromCache(String key) {
        return Optional.ofNullable((Map<Long, DailyRentalCharge>) hashOperations.get(CacheConstants.DAILY_RENTAL_CHARGES_CACHE, key));
    }

    @Override
    public void clearToolsCache(String hashKey) {
        hashOperations.delete(CacheConstants.TOOLS_CACHE, hashKey);
    }

    @Override
    public void clearDailyRentalChargesCache(String hashKey) {
        hashOperations.delete(CacheConstants.DAILY_RENTAL_CHARGES_CACHE, hashKey);
    }
}
