package com.civalue.eugene.services;

import com.civalue.eugene.data.entities.Shopper;
import com.civalue.eugene.model.ShopperMetadata;

import java.util.List;

public interface ShopperService {

    Shopper save(Shopper shopper);
    List<Shopper> saveAll(List<Shopper> shoppers);
    List<Shopper> convertModelsAndSave(List<ShopperMetadata> models);
    Shopper findById(Long id);
    List<Shopper> findByBrand(String brand);
    List<Shopper> findByCategory(String category);
    List<Shopper> findByProductId(String productID, int limit);
    List<ShopperMetadata> toModels(List<Shopper> shoppers);
}
