package com.example.Category.Management.Controller;
import com.example.Category.Management.Dtos.ProductEntryDto;
import com.example.Category.Management.Dtos.ProductResponseDto;
import com.example.Category.Management.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<String> addProduct(@RequestBody ProductEntryDto product){
        String response = productService.addProduct(product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-product/{name}")
    public  ResponseEntity<Object> getProduct(@PathVariable String name){
        Object product = productService.getProduct(name);
        return new ResponseEntity<>(product , HttpStatus.OK);
    }

    @DeleteMapping("/delete-product/{name}")
    public ResponseEntity<String> deleteProduct(@PathVariable String name){
        String response = productService.deleteProduct(name);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @PutMapping("/update-product-details")
    public ResponseEntity<String> updateProduct(@RequestParam String name , String property , int value){
        String response = productService.updateProduct(name, property, value);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
