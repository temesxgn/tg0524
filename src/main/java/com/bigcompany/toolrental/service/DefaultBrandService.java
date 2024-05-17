package com.bigcompany.toolrental.service;

import com.bigcompany.toolrental.exception.BrandNotFoundException;
import com.bigcompany.toolrental.exception.ToolTypeNotFoundException;
import com.bigcompany.toolrental.mapper.BrandMapper;
import com.bigcompany.toolrental.model.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultBrandService implements BrandService {

    private static final String BRANDS_CACHE_HASH_KEY = "findAllBrands";

    private final BrandMapper mapper;
    private final CacheService cacheService;

    @Override
    public List<Brand> findAll() {
        return cacheService.getBrandsFromCache(BRANDS_CACHE_HASH_KEY)
                .map(Map::values)
                .map(List::copyOf)
                .orElseGet(() -> {
                    List<Brand> brands = mapper.findAll();
                    if (!brands.isEmpty()) {
                        Map<Long, Brand> brandMap = brands.stream()
                                .collect(Collectors.toMap(Brand::getId, brand -> brand));
                        cacheService.put(BRANDS_CACHE_HASH_KEY, brandMap);
                    }
                    return brands;
                });
    }

    @Override
    public Brand findById(Long id) throws ToolTypeNotFoundException {
        return cacheService.getBrandsFromCache(BRANDS_CACHE_HASH_KEY)
                .map(brandMap -> brandMap.get(id))
                .or(() -> mapper.findById(id))
                .orElseThrow(() -> new BrandNotFoundException(String.format("Brand with id %s not found", id)));
    }

    @Override
    public Long insert(Brand brand) {
        Long id = mapper.insert(brand);
        cacheService.getBrandsFromCache(BRANDS_CACHE_HASH_KEY)
                .ifPresent((brandMap) -> {
                    brand.setId(id);
                    brandMap.put(id, brand);
                    cacheService.put(BRANDS_CACHE_HASH_KEY, brandMap);
                });
        return id;
    }
}
