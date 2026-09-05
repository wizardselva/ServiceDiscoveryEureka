package com.example.productcatalogservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
//    private String name;
//    private String description;
//    private String imageUrl;
//    private Double price;
//    private Category category;
//    private Boolean isSaleEligible;

    private String name;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Category category;
    private Double price;
    private String imageUrl;
}
