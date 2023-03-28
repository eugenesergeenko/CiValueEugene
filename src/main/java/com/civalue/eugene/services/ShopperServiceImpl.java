package com.civalue.eugene.services;

import com.civalue.eugene.data.entities.Product;
import com.civalue.eugene.data.entities.Shopper;
import com.civalue.eugene.data.entities.ShopperProduct;
import com.civalue.eugene.data.repos.ShopperRepository;
import com.civalue.eugene.model.ShelfItem;
import com.civalue.eugene.model.ShopperMetadata;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ShopperServiceImpl implements ShopperService {

    private ShopperRepository shopperRepository;
    private ProductService productService;

    @Override
    public Shopper save(Shopper shopper) {
        return shopperRepository.save(shopper);
    }

    @Override
    public List<Shopper> saveAll(List<Shopper> shoppers) {
        return shopperRepository.saveAll(shoppers);
    }

    @Override
    public List<Shopper> convertModelsAndSave(List<ShopperMetadata> models) {
        List<Shopper> shoppers = new ArrayList<>();
        for(ShopperMetadata shopperMetadata : models) {
            shoppers.add(modelToEntity(shopperMetadata));
        }
        return saveAll(shoppers);
    }

    @Override
    public Shopper findById(Long id) {
        return shopperRepository.findById(id).orElse(null);
    }

    @Override
    public List<Shopper> findByBrand(String brand) {
        return null;
    }

    @Override
    public List<Shopper> findByCategory(String category) {
        return null;
    }

    @Override
    public List<Shopper> findByProductId(String productID, int limit) {
        return shopperRepository.getByProduct(productID, limit);
    }

    @Override
    public List<ShopperMetadata> toModels(List<Shopper> shoppers) {
        List<ShopperMetadata> models = new ArrayList<>(shoppers.size());
        for(Shopper shopper : shoppers) {
            ShopperMetadata shopperMetadata = new ShopperMetadata();
            shopperMetadata.setShopperId(shopper.getShopperId());
            for(ShopperProduct sp : shopper.getProducts()) {
                ShelfItem shelfItem = new ShelfItem(sp.getProduct().getProductId(), sp.getRelevancyScore());
                shopperMetadata.addShelfItem(shelfItem);
            }
            models.add(shopperMetadata);
        }
        return models;
    }

    private Shopper modelToEntity(ShopperMetadata shopperMetadata) {
        Shopper shopper = new Shopper();
        shopper.setShopperId(shopperMetadata.getShopperId());
        List<String> productsIds = new ArrayList<>();

        Map<String, Double> relevancyMap = new HashMap<>();
        for(ShelfItem shelfItem : shopperMetadata.getShelf()) {
            productsIds.add(shelfItem.getProductId());
            relevancyMap.put(shelfItem.getProductId(), shelfItem.getRelevancyScore());
        }
        List<Product> products = productService.findAllByProductId(productsIds);
        for(Product p : products) {
            shopper.addProduct(p, relevancyMap.get(p.getProductId()));
        }
        return shopper;
    }
}
