package com.userauthservice.userauthservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserSession extends BaseModel{
    private String token;

    //1 user can M session -> Scler 1:2
    @ManyToOne
    private User user;
}
