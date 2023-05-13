package com.example.Category.Management.Repositories;

import com.example.Category.Management.Entites.ProductCategoryEntity;
import com.example.Category.Management.Entites.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.*;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query(value = "select * from product_entity where  product_name = :productName",nativeQuery = true)
    public ProductEntity findByName(@Param("productName") String productName);
}