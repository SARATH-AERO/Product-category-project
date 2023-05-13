package com.example.Category.Management.Controller;

import com.example.Category.Management.Dtos.ProductResponseDto;
import com.example.Category.Management.Entites.ProductEntity;
import com.example.Category.Management.Services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-category")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @PostMapping("/add-product-category/{name}")
    public ResponseEntity<String> addProductCategory(@PathVariable String name){
        String response = productCategoryService.addProductCategory(name);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @GetMapping("/get-product-category/{name}")
    public ResponseEntity<List<ProductResponseDto>> getProductCategory(@PathVariable String name){
        List<ProductResponseDto> list = productCategoryService.getProductCategory(name);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @DeleteMapping("/delete-product-category/{name}")
    public ResponseEntity<String> deleteProductCategory(@PathVariable String name){
        String response = productCategoryService.deleteProductCategory(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
