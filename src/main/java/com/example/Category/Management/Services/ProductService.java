package com.example.Category.Management.Services;

import com.example.Category.Management.Dtos.ProductEntryDto;
import com.example.Category.Management.Dtos.ProductResponseDto;
import com.example.Category.Management.Entites.*;
import com.example.Category.Management.Repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductCategoryService productCategoryService;
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
        product1.setBrand(product.getBrand());
        product1.setQuantity(product.getQuantity());
        product1.setColor(product.getColor());
        product1.setDescription(product.getDescription());

        List<ProductEntity> list = productCategoryEntity.getProducts();
        list.add(product1);
        productCategoryEntity.setProducts(list);
        product1.setProductCategoryEntity(productCategoryEntity);
        productCategoryRepository.save(productCategoryEntity);

        return "successful";

    }

    public ProductResponseDto getProduct(String name) {
        ProductEntity product = productRepository.findByName(name);
        ProductResponseDto product1 = new ProductResponseDto();
        product1.setProductName(product.getProductName());
        product1.setProductCategoryName(product.getProductCategoryName());
        product1.setBrand(product.getBrand());
        product1.setQuantity(product.getQuantity());
        product1.setColor(product.getColor());
        product1.setDescription(product.getDescription());
        return product1;
    }

    public String deleteProduct(int productId) {
        productRepository.deleteById(productId);
        return "sdfsdf";
    }

    public String updateProduct(String name, String property, int value) {
        ProductEntity product = productRepository.findByName(name);
        if(property.equals("price")) {
            product.setPrice(value);
        }else if(property.equals("quantity")){
            product.setQuantity(value);
        }
        productRepository.save(product);
        return "successful";
    }
}
