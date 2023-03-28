package com.civalue.eugene.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductSearchRequest {

    private String shopperId;
    private String categoryId;
    private String brand;
    private int limit;

    public boolean hasCategory() {
        return StringUtils.isNotEmpty(categoryId);
    }

    public boolean hasBrand() {
        return StringUtils.isNotEmpty(brand);
    }
}
