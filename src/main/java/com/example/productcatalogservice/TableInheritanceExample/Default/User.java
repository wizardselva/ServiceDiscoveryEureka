package com.example.productcatalogservice.TableInheritanceExample.Default;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import java.util.UUID;

@Entity(name="users")
public class User {
    @Id
    private UUID id;
    private String name;
}
