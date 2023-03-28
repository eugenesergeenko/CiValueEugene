package com.civalue.eugene.data.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ShopperProductId implements Serializable {

    private Long shopperId;
    private Long productId;

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || this.getClass() != obj.getClass())
            return false;
        ShopperProductId that = (ShopperProductId) obj;
        return Objects.equals(this.shopperId, that.shopperId) && Objects.equals(this.productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.shopperId, this.productId);
    }
}

