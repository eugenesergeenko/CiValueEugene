package com.civalue.eugene.services;

import com.civalue.eugene.data.entities.Brand;
import com.civalue.eugene.data.repos.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> saveAll(List<Brand> brands) {
        return brandRepository.saveAll(brands);
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    @Override
    public Brand findByName(String name) {
        return brandRepository.findByName(name).orElse(null);
    }
}
