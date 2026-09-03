package com.example.productcatalogservice.TableInheritanceExample.TablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity(name = "tpc_ta")
public class TA extends User{
    private int helpRequests;
}
