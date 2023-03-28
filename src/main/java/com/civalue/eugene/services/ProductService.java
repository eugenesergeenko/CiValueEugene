package com.civalue.eugene.services;

import com.civalue.eugene.data.entities.Product;
import com.civalue.eugene.model.ProductSearchRequest;

import java.util.List;

public interface ProductService {

    Product save(Product product);
    List<Product> saveAll(List<Product> products);
    List<Product> findAll();
    Product findById(Long id);
    List<Product> findbyIds(List<Long> ids);
    List<Product> findAllByProductId(List<String> productIds);
    List<Product> findBySearchRequest(ProductSearchRequest productSearchRequest);

}
