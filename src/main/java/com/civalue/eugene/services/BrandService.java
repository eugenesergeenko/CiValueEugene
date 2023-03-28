package com.civalue.eugene.services;

import com.civalue.eugene.data.entities.Brand;

import java.util.List;

public interface BrandService {

    Brand save(Brand brand);
    List<Brand> saveAll(List<Brand> brands);
    Brand findById(Long id);
    Brand findByName(String name);
}
