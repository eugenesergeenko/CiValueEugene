package com.civalue.eugene.services;

import com.civalue.eugene.data.entities.Product;
import com.civalue.eugene.data.repos.ProductRepository;
import com.civalue.eugene.model.ProductSearchRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> saveAll(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public List<Product> findbyIds(List<Long> ids) {
        return productRepository.findByIdIn(ids);
    }

    @Override
    public List<Product> findAllByProductId(List<String> productIds) {
        return productRepository.findByProductIdInIgnoreCase(productIds);
    }

    @Override
    public List<Product> findBySearchRequest(ProductSearchRequest productSearchRequest) {
        return productRepository.findByProductSearchRequest(productSearchRequest);
    }
}
