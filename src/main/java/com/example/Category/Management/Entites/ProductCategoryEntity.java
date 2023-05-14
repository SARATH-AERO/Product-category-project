package com.example.Category.Management.Entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    private String productCategory;

    @OneToMany(mappedBy = "productCategoryEntity",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ProductEntity> products = new ArrayList<>();
}
