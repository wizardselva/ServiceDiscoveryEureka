package com.example.productcatalogservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public abstract class BaseModel {
    @Id
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private State state;

    public BaseModel(){
        this.createdAt=new Date();
        this.lastUpdatedAt=new Date();
        this.state=State.ACTIVE;
    }
}
