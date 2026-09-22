package com.example.productcatalogservice.services;

import com.example.productcatalogservice.dtos.SearchRequestDto;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SearchService {

    @Autowired
    private ProductRepo productRepo;

    public Page<Product> searchProducts(String query, Integer pageSize, Integer pageNumber) {
//        Sort sort = Sort.by("price");
        Sort sortByPriceDesc = Sort.by("price").descending();
        Sort sortByIdDesc = Sort.by("id").descending();
        Sort sort=sortByPriceDesc.and(sortByIdDesc);
        return productRepo.findByName(query, PageRequest.of(pageNumber, pageSize,sort));
    }
}

/*
---------HW---------------

        {
        "query" : "laptop",
        "pageSize" : 6,
        "pageNumber" : 0,
        "sortParams" : {
        {
        "sortCriteria" : "price",
        "sortType" : "ASC"
        },
        {
        "sortCriteria" : "id",
        "sortType" : "DESC"
        },
        {
        "sortCriteria" : "description",
        "sortType" : "ASC"
        }
        }
        }

 */