package com.example.Category.Management;

import com.example.Category.Management.Controller.ProductCategoryController;
import com.example.Category.Management.Controller.ProductController;
import com.example.Category.Management.Dtos.ProductEntryDto;
import com.example.Category.Management.Entites.ProductCategoryEntity;
import com.example.Category.Management.Entites.ProductEntity;
import com.example.Category.Management.Repositories.ProductCategoryRepository;
import com.example.Category.Management.Repositories.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CategoryManagementApplication.class)
class CategoryManagementApplicationTests {

	@Autowired
	ProductCategoryController productCategoryController;
	@Autowired
	ProductController productController;

	@Autowired
	ProductCategoryRepository productCategoryRepository;
	@Autowired
	ProductRepository productRepository;

	@AfterEach
	@Transactional
	public void remove(){
		productRepository.removeAllRowsProductEntity();
	}

	@Test
	public void addProductCategory() {
		productCategoryController.addProductCategory("mobiles");
		ProductCategoryEntity productCategoryEntity = productCategoryRepository.findByName("mobiles");
		assertEquals(productCategoryEntity.getProductCategory() , "mobiles");
	}

	@Test
	public void getProductCategory(){
		productCategoryController.addProductCategory("belts");
		ProductEntryDto product = new ProductEntryDto("belts","rolex belts","this is rolex watcch",100000,"gold",2,"rolex");
		ResponseEntity<String> response = productController.addProduct(product);
		ResponseEntity<Object> response1 = productCategoryController.getProductCategory("belts");
		Objects.requireNonNull(response1.getBody());
	}
	@Test
	public void deleteProductCategory(){
		productCategoryController.addProductCategory("tie");
		ResponseEntity<String> response = productCategoryController.deleteProductCategory("tie");
		assertEquals(response.getBody() , "product category deleted successfully");

	}

	@Test
	public void addProduct(){
		ProductEntryDto productEntryDto = new ProductEntryDto("watch","rolex watch","this is rolex watcch",100000,"gold",2,"rolex");
		ResponseEntity<String> response = productController.addProduct(productEntryDto);
		assertEquals(response.getBody() , "rolex watch product added successfully");

	}

	@Test
	public void getProduct(){
		ProductEntryDto product = new ProductEntryDto("bottles","mario bottle","this is rolex watcch",100000,"gold",2,"rolex");
		ResponseEntity<String> response = productController.addProduct(product);
		ResponseEntity<Object> response1 = productCategoryController.getProductCategory("bottles");
		Objects.requireNonNull(response1.getBody());

	}

	@Test
	public void deleteProduct(){
		ProductEntryDto product = new ProductEntryDto("pens","hero pen","this is rolex watcch",100000,"gold",2,"rolex");
		ResponseEntity<String> response = productController.addProduct(product);
		ResponseEntity<String> response1 = productController.deleteProduct("hero pen");
		assertEquals(response1.getBody() , "hero pen product removed successfully");
	}

	@Test
	public void updateProduct(){
		ProductEntryDto product = new ProductEntryDto("box","adidas box","this is rolex watch",100000,"gold",2,"adidas");
		ResponseEntity<String> response = productController.addProduct(product);
		ResponseEntity<String> response1 = productController.updateProduct("adidas box","price",50);
		assertEquals(response1.getBody() , "adidas box price details updated 50 successfully");


	}

}
