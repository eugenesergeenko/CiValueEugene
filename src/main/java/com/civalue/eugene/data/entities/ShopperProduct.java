package com.civalue.eugene.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "SHOPPERS_PRODUCTS")
@Getter
@Setter
public class ShopperProduct {

    @EmbeddedId
    private ShopperProductId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("shopperId")
    private Shopper shopper;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    @Column(name = "RELEVANCY_SCORE")
    private Double relevancyScore;

    private ShopperProduct() {}

    public ShopperProduct(Shopper shopper, Product product) {
        this.shopper = shopper;
        this.product = product;
        this.id = new ShopperProductId(shopper.getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopper, product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ShopperProduct that = (ShopperProduct) o;
        return Objects.equals(this.shopper, that.shopper) &&
                Objects.equals(this.product, that.product);
    }
}
