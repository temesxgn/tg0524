package com.bigcompany.toolrental.service;

import com.bigcompany.toolrental.exception.ToolTypeNotFoundException;
import com.bigcompany.toolrental.model.Brand;

import java.util.List;

public interface BrandService {

    List<Brand> findAll();
    Brand findById(Long id) throws ToolTypeNotFoundException;

    Long insert(Brand brand);
}
