package com.civalue.eugene.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShopperMetadata {
    private String shopperId;
    private List<ShelfItem> shelf;

    public void addShelfItem(ShelfItem shelfItem) {
        if(shelf == null)
            shelf = new ArrayList<>();
        shelf.add(shelfItem);
    }

}
