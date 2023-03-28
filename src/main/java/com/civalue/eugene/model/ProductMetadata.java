package com.civalue.eugene.model;

import com.civalue.eugene.data.entities.Category;
import com.civalue.eugene.data.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductMetadata {
    private String productId;
    private String category;
    private String brand;

    public ProductMetadata(Product product) {
        this.productId = product.getProductId();
        this.category = String.join(",", product.getCategories().stream().map(Category::getName).collect(Collectors.toList()));
        this.brand = product.getBrand().getName();
    }
}
