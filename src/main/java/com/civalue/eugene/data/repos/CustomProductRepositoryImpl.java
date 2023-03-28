package com.civalue.eugene.data.repos;

import com.civalue.eugene.data.entities.Product;
import com.civalue.eugene.model.ProductSearchRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class CustomProductRepositoryImpl implements CustomProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findByProductSearchRequest(ProductSearchRequest productSearchRequest) {
        String query = buildQuery(productSearchRequest);
        System.out.println("Generated query: " + query);
        TypedQuery<Product> typedQuery = entityManager.createQuery(query, Product.class);
        return typedQuery.getResultList();
    }

    private String buildQuery(ProductSearchRequest productSearchRequest) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT p.* FROM products p");
        sb.append(" join shoppers_products sp ON p.ID = sp.PRODUCT_ID");
        if(productSearchRequest.hasCategory()) {
            sb.append(" JOIN products_categories pc ON p.ID = pc.PRODUCT_ID");
        }
        if(productSearchRequest.hasBrand()) {
            sb.append(" JOIN brands b ON p.BRAND_ID = b.ID");
        }
        sb.append(" WHERE sp.SHOPPER_ID IN (SELECT s.id FROM shoppers s WHERE s.SHOPPER_ID = '" + productSearchRequest.getShopperId() + "')");
        if(productSearchRequest.hasCategory()) {
            sb.append(" AND pc.CATEGORY_ID IN (SELECT c.id FROM categories c WHERE c.NAME = '" + productSearchRequest.getCategoryId() + "')");
        }
        if(productSearchRequest.hasBrand()) {
            sb.append(" AND b.NAME = '" + productSearchRequest.getBrand() + "'");
        }
        sb.append(" limit " + productSearchRequest.getLimit());
        return sb.toString();
    }
}
