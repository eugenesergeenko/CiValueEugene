package com.civalue.eugene.data.repos;

import com.civalue.eugene.data.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>, CustomProductRepository {
    List<Product> findByIdIn(Collection<Long> ids);
    List<Product> findByProductIdInIgnoreCase(Collection<String> productIds);
    List<Product> findByBrand_NameIgnoreCaseOrderByBrand_NameAsc(String name);
}