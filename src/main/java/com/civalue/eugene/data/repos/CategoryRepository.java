package com.civalue.eugene.data.repos;

import com.civalue.eugene.data.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, CrudRepository<Category, Long> {
    Optional<Category> findFirstByNameIgnoreCase(String name);
}