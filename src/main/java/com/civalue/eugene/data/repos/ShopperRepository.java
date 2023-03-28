package com.civalue.eugene.data.repos;

import com.civalue.eugene.data.entities.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShopperRepository extends JpaRepository<Shopper, Long>, CrudRepository<Shopper, Long> {

    @Query(value = "SELECT s.* FROM shoppers s JOIN shoppers_products sp ON s.ID = sp.SHOPPER_ID JOIN products p ON p.ID = sp.PRODUCT_ID" +
            " WHERE p.PRODUCT_ID = ?1 limit ?2", nativeQuery = true)
    List<Shopper> getByProduct(String productId, int limit);
}