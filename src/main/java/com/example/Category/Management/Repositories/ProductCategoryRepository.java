package com.example.Category.Management.Repositories;

import com.example.Category.Management.Entites.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.*;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity,Integer> {

    @Query(value = "select * from product_category_entity where  product_category = :productCategory",nativeQuery = true)
    public ProductCategoryEntity findByName(@Param("productCategory") String productCategoryName);
}
