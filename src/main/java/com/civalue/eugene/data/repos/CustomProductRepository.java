package com.civalue.eugene.data.repos;

import com.civalue.eugene.data.entities.Product;
import com.civalue.eugene.model.ProductSearchRequest;

import java.util.List;

public interface CustomProductRepository {
    List<Product> findByProductSearchRequest(ProductSearchRequest productSearchRequest);
}
