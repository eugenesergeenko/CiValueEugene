package com.civalue.eugene.services;

import com.civalue.eugene.data.entities.Category;

import java.util.List;

public interface CategoriesService {

    Category save(Category category);
    List<Category> saveAll(List<Category> categories);
    Category findById(Long id);
    Category findByName(String name);
}
