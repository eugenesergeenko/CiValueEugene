package com.civalue.eugene.controllers;

import com.civalue.eugene.data.entities.Brand;
import com.civalue.eugene.data.entities.Category;
import com.civalue.eugene.data.entities.Product;
import com.civalue.eugene.data.entities.Shopper;
import com.civalue.eugene.data.repos.ProductRepository;
import com.civalue.eugene.model.ProductMetadata;
import com.civalue.eugene.model.ShopperMetadata;
import com.civalue.eugene.services.BrandService;
import com.civalue.eugene.services.CategoriesService;
import com.civalue.eugene.services.ProductService;
import com.civalue.eugene.services.ShopperService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/civalue/datateam")
@AllArgsConstructor
public class DataTeamController {

    private BrandService brandService;
    private CategoriesService categoriesService;
    private ProductService productService;
    private ShopperService shopperService;
    private final ProductRepository productRepository;


    @GetMapping("/preload")
    public ResponseEntity<?> preload() {
        List<Brand> brands = fakeBrands();
        List<Category> categories = fakeCategories();
        List<Product> products = fakeProducts(brands, categories);
        return new ResponseEntity<>(products.size() + " products were successfully loaded", HttpStatus.OK);
    }

    @PostMapping("/shoppers")
    public ResponseEntity<?> saveShoppers(@RequestBody List<ShopperMetadata> shoppers) {
        List<Shopper> res = shopperService.convertModelsAndSave(shoppers);
        return new ResponseEntity<>(res.size() + " Shoppers were successfylly loaded", HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable(value = "productId") String productId) {
        Product product = productService.findAllByProductId(Arrays.asList(productId)).get(0) ;
        return new ResponseEntity<>(new ProductMetadata(product), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProduct() {
        List<Product> products = productService.findAll();
        List<ProductMetadata> response = products.stream().map(product -> new ProductMetadata(product)).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private List<Brand> fakeBrands() {
        List<Brand> brands = new ArrayList<>();
        for(int i=1; i<8; i++) {
            Brand b = new Brand();
            b.setName("BRAND " + i);
            brands.add(b);
        }
        return brandService.saveAll(brands);
    }

    private List<Category> fakeCategories() {
        List<Category> categories = new ArrayList<>();
        for(int i=1; i<11; i++) {
            Category c = new Category();
            c.setName("CAT " + i);
            categories.add(c);
        }
        return categoriesService.saveAll(categories);
    }

    private List<Product> fakeProducts(List<Brand> brands, List<Category> categories) {
        List<Product> products = new ArrayList<>();
        for(int i=0; i<100; i++) {
            Product p = new Product();
            p.setProductId("PROD_" + i);
            p.setBrand(getRandomBrand(brands));
            p.setCategories(new HashSet<>(randomCategories(categories, i)));
            products.add(p);
        }
        return productService.saveAll(products);
    }

    private Brand getRandomBrand(List<Brand> brands) {
        return brands.get(randomInRange(0, brands.size() -1));
    }

    private List<Category> randomCategories(List<Category> categories, int idx) {
        List<Category> list = new ArrayList<>();
        list.add(categories.get(randomInRange(0, categories.size() -1)));
        if(idx % 2 == 0) {
            list.add(categories.get(randomInRange(0, categories.size() -1)));
        }
        return list;
    }

    private int randomInRange(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
