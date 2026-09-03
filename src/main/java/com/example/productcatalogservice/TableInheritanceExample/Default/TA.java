package com.example.productcatalogservice.TableInheritanceExample.Default;

import jakarta.persistence.Entity;

@Entity(name = "ta")
public class TA extends User {
    private int helpRequests;
}
