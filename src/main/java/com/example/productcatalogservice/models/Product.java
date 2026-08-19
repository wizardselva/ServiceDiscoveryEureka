package com.example.productcatalogservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
//    private String name;
//    private String description;
//    private String imageUrl;
//    private Double price;
//    private Category category;
//    private Boolean isSaleEligible;

    private String title;
    private String description;
    private String category;
    private Double price;
    private String image;
}
