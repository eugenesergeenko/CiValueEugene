package com.civalue.eugene.controllers;

import com.civalue.eugene.data.entities.Product;
import com.civalue.eugene.data.entities.Shopper;
import com.civalue.eugene.data.repos.BrandRepository;
import com.civalue.eugene.model.ProductMetadata;
import com.civalue.eugene.model.ProductSearchRequest;
import com.civalue.eugene.services.ProductService;
import com.civalue.eugene.services.ShopperService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/civalue/ecommerce")
@AllArgsConstructor
public class ECommerceController {

    private static final int PRODUCTS_MAX = 100;
    private static final int SHOPPERS_MAX = 1000;

    private ShopperService shopperService;
    private ProductService productService;

    @GetMapping("/shopper/products")
    public ResponseEntity<?> getProductsForShopper(@RequestParam(name = "shopperId") String shopperId,
                                                   @RequestParam(name = "category", required = false) String category,
                                                   @RequestParam(name = "brand", required = false) String brand,
                                                   @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {
        if(limit > PRODUCTS_MAX)
            limit = PRODUCTS_MAX;
        ProductSearchRequest productSearchRequest = new ProductSearchRequest(shopperId, category, brand, limit);
        List<Product> products = productService.findBySearchRequest(productSearchRequest);
        if(products != null && products.isEmpty()) {
            List<ProductMetadata> response = products.stream().map(product -> new ProductMetadata(product)).collect(Collectors.toList());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Nothing found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/shoppers")
    public ResponseEntity<?> getShoppersByProduct(@RequestParam(name = "productId") String productId,
                                                  @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {

        if(limit > SHOPPERS_MAX)
            limit = SHOPPERS_MAX;

        List<Shopper> shoppers = shopperService.findByProductId(productId, limit);
        return new ResponseEntity<>(shopperService.toModels(shoppers), HttpStatus.OK);
    }
}
