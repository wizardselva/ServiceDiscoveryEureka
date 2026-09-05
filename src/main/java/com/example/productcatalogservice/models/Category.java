package com.example.productcatalogservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String name;
    private String description;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
//    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
//    @Fetch(FetchMode.JOIN)
//    @Fetch(FetchMode.SUBSELECT) // Lazy + Select = N+1 pblm
//                                // Lazy + Subselect = Pblm fixed
    @BatchSize(size = 10) // 1 category query
                         // 2 + 2 + 1 (If 5 data)
                         // total = 4 query
    private List<Product> products;
}
