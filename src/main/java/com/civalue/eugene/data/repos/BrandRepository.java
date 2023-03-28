package com.civalue.eugene.data.repos;

import com.civalue.eugene.data.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>, CrudRepository<Brand, Long> {

    Optional<Brand> findByName(String name);

}
