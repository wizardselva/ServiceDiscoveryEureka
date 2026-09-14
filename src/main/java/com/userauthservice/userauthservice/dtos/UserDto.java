package com.userauthservice.userauthservice.dtos;

import com.userauthservice.userauthservice.models.Role;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String email;

    @ManyToMany
    private List<String> roles;
}
