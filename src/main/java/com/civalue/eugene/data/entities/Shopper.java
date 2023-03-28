package com.civalue.eugene.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SHOPPERS")
@NaturalIdCache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Shopper {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    private String shopperId;

    @OneToMany(mappedBy = "shopper", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShopperProduct> products = new ArrayList<>();

    public void addProduct(Product product, Double relevancyScore) {
        ShopperProduct shopperProduct = new ShopperProduct(this, product);
        shopperProduct.setRelevancyScore(relevancyScore);
        products.add(shopperProduct);
        product.getShoppers().add(shopperProduct);
    }

    public void removeProduct(Product product) {
        for(Iterator<ShopperProduct> iterator = products.iterator(); iterator.hasNext();) {
            ShopperProduct shopperProduct = iterator.next();
            if(shopperProduct.getShopper().equals(this) && shopperProduct.getProduct().equals(product)) {
                iterator.remove();
                shopperProduct.getProduct().getShoppers().remove(shopperProduct);
                shopperProduct.setShopper(null);
                shopperProduct.setProduct(null);
            }
        }
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shopper)) return false;
        return id != null && id.equals(((Shopper) o).getId());    }
}
