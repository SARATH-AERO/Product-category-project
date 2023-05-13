package com.example.Category.Management.Entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private String productCategoryName;

    private String productName;

    private String description;

    private double price;

    private String color;

    private int quantity;

    private String brand;

    @ManyToOne
    @JoinColumn
    private ProductCategoryEntity productCategoryEntity;
}
