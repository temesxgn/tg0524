package com.bigcompany.toolrental.service;

import com.bigcompany.toolrental.exception.ToolNotFoundException;
import com.bigcompany.toolrental.mapper.ToolMapper;
import com.bigcompany.toolrental.model.Brand;
import com.bigcompany.toolrental.model.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class ToolService {

    private static final String TOOLS_CACHE_HASH_KEY = "findAllTools";

    private final ToolMapper mapper;
    private final CacheService cacheService;

    public List<Tool> findAll() {
        return cacheService.getToolsFromCache(TOOLS_CACHE_HASH_KEY)
                .map(Map::values)
                .map(List::copyOf)
                .orElseGet(() -> {
                    List<Tool> tools = mapper.findAll();
                    Map<Long, Tool> toolMap = tools.stream()
                            .collect(Collectors.toMap(Tool::getId, tool -> tool));
                    if (!tools.isEmpty()) {
                        cacheService.put(TOOLS_CACHE_HASH_KEY, toolMap);
                    }
                    return tools;
                });
    }

    public Tool findByCode(String code) throws ToolNotFoundException {
        return findAll()
                .stream()
                .filter(tool -> tool.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new ToolNotFoundException(String.format("Tool with code %s not found", code)));
    }
}
