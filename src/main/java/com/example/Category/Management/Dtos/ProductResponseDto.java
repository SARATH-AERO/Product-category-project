package com.example.Category.Management.Dtos;

import lombok.Data;

@Data
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
