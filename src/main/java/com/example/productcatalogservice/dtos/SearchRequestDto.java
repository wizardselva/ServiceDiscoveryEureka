package com.example.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class SearchRequestDto {
    private String Query;
    private Integer pageSize;
    private Integer pageNumber;
}
