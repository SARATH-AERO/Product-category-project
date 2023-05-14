package com.example.Category.Management.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private int id;

    private String productCategoryName;

    private String productName;

    private String description;

    private double price;

    private String color;

    private int quantity;

    private String brand;
}
