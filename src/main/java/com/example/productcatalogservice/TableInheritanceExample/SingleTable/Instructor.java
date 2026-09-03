package com.example.productcatalogservice.TableInheritanceExample.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "st_instructor")
@DiscriminatorValue(value = "INST")
public class Instructor extends User {
    private String company;
}
