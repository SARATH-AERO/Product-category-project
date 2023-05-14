package com.example.Category.Management.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntryDto {

    private String productCategoryName;

    private String productName;

    private String description;

    private double price;

    private String color;

    private int quantity;

    private String brand;
}
