package com.example.Category.Management.Services;

import com.example.Category.Management.Dtos.ProductResponseDto;
import com.example.Category.Management.Entites.ProductCategoryEntity;
import com.example.Category.Management.Entites.ProductEntity;
import com.example.Category.Management.Repositories.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;
    public String addProductCategory(String name) {
        ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity();
        productCategoryEntity.setProductCategory(name);
        productCategoryRepository.save(productCategoryEntity);
        return "successful";
    }

    public List<ProductResponseDto> getProductCategory(String name) {
        if(productCategoryRepository.findByName(name) != null) {
            ProductCategoryEntity productCategoryEntity = productCategoryRepository.findByName(name);
            List<ProductEntity> productEntities = productCategoryEntity.getProducts();
            List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

            for(ProductEntity product : productEntities){
                ProductResponseDto product1 = new ProductResponseDto();
                product1.setProductName(product.getProductName());
                product1.setProductCategoryName(product.getProductCategoryName());
                product1.setBrand(product.getBrand());
                product1.setQuantity(product.getQuantity());
                product1.setColor(product.getColor());
                product1.setDescription(product.getDescription());
                productResponseDtoList.add(product1);
            }
            return productResponseDtoList;
        }else {
            return null;
        }
    }

    public String deleteProductCategory(String name) {
        if(productCategoryRepository.findByName(name) != null) {
            ProductCategoryEntity productCategoryEntity = productCategoryRepository.findByName(name);
            productCategoryRepository.deleteById(productCategoryEntity.getId());
            return "succerss";
        }
        else
            return "fdailuer";

    }
}
