package com.bigcompany.toolrental.service;

import com.bigcompany.toolrental.exception.ToolTypeNotFoundException;
import com.bigcompany.toolrental.mapper.ToolTypeMapper;
import com.bigcompany.toolrental.model.Brand;
import com.bigcompany.toolrental.model.ToolType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToolTypeService {

    private static final String TOOL_TYPES_CACHE_HASH_KEY = "findAllToolTypes";

    private final ToolTypeMapper mapper;
    private final CacheService cacheService;

    public List<ToolType> findAll() {
        return cacheService.getToolTypesFromCache(TOOL_TYPES_CACHE_HASH_KEY)
                .map(Map::values)
                .map(List::copyOf)
                .orElseGet(() -> {
                    List<ToolType> types = mapper.findAll();
                    Map<Long, ToolType> typeMap = types.stream()
                            .collect(Collectors.toMap(ToolType::getId, type -> type));
                    if (!types.isEmpty()) {
                        cacheService.put(TOOL_TYPES_CACHE_HASH_KEY, types);
                    }
                    return types;
                });
    }

    public ToolType findById(Long id) throws ToolTypeNotFoundException {
        return findAll()
                .stream()
                .filter(tool -> tool.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ToolTypeNotFoundException(String.format("Tool type with id %s not found", id)));
    }
}
