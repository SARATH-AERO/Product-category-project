package com.example.Category.Management.Services;

import com.example.Category.Management.Dtos.ProductEntryDto;
import com.example.Category.Management.Dtos.ProductResponseDto;
import com.example.Category.Management.Entites.*;
import com.example.Category.Management.Repositories.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.*;

@Service
public class ProductService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;
    @Autowired
    ProductRepository productRepository;

    public String addProduct(ProductEntryDto product) {
        ProductCategoryEntity productCategoryEntity;
        if(productCategoryRepository.findByName(product.getProductCategoryName()) != null){
            productCategoryEntity = productCategoryRepository.findByName(product.getProductCategoryName());
        } else{
            productCategoryEntity = new ProductCategoryEntity();
            productCategoryEntity.setProductCategory(product.getProductCategoryName());
            productCategoryRepository.save(productCategoryEntity);
        }

        ProductEntity product1 = new ProductEntity();
        product1.setProductName(product.getProductName());
        product1.setProductCategoryName(product.getProductCategoryName());
        product1.setPrice(product.getPrice());
        product1.setBrand(product.getBrand());
        product1.setQuantity(product.getQuantity());
        product1.setColor(product.getColor());
        product1.setDescription(product.getDescription());

        List<ProductEntity> list = productCategoryEntity.getProducts();
        for(ProductEntity productEntity : list){
            if(productEntity.getProductName().equalsIgnoreCase(product1.getProductName()))
                return product.getProductName() +" product already exists";
        }
        productRepository.save(product1);
        product1.setProductCategoryEntity(productCategoryEntity);
        list.add(product1);
        productCategoryEntity.setProducts(list);
        productCategoryRepository.save(productCategoryEntity);
        return product1.getProductName()+" product added successfully";

    }

    public Object getProduct(String name) {
        if(productRepository.findByName(name) != null) {
            ProductEntity product = productRepository.findByName(name);
            ProductResponseDto product1 = new ProductResponseDto();
            product1.setId(product.getProductId());
            product1.setProductName(product.getProductName());
            product1.setProductCategoryName(product.getProductCategoryName());
            product1.setBrand(product.getBrand());
            product1.setPrice(product.getPrice());
            product1.setQuantity(product.getQuantity());
            product1.setColor(product.getColor());
            product1.setDescription(product.getDescription());
            return product1;
        }
        return "product "+name+" is invalid";
    }

    public String deleteProduct(String productName) {
        if(productRepository.findByName(productName) != null ) {
            ProductEntity product = productRepository.findByName(productName);
            int productId = product.getProductId();
            productRepository.deleteById(productId);
            return productName+" product removed successfully";
        }else
            return productName+" product name is invalid";
    }

    public String updateProduct(String name, String property, int value) {
        if(property.equalsIgnoreCase("price") == false && property.equalsIgnoreCase("quantity") == false)
            return "cannot update " + property ;
        if (productRepository.findByName(name) != null) {
            ProductEntity product = productRepository.findByName(name);
            ProductCategoryEntity productCategoryEntity = productCategoryRepository.findByName(product.getProductCategoryName());
            List<ProductEntity> list = productCategoryEntity.getProducts();

            list.remove(product);
            if (property.equalsIgnoreCase("price")) {
                product.setPrice(value);
            } else if (property.equalsIgnoreCase("quantity"))
            {
                product.setQuantity(value);
            }
            list.add(product);
            productCategoryEntity.setProducts(list);
            product.setProductCategoryEntity(productCategoryEntity);
            productRepository.save(product);
            return product.getProductName()+" "+property+" details updated "+value+" successfully";

        }else
            return "product "+name+" is invalid";

    }

}
