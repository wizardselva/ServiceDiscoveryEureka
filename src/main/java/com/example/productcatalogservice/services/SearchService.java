package com.example.productcatalogservice.services;

import com.example.productcatalogservice.dtos.SearchRequestDto;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SearchService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> searchProducts(String query, Integer pageSize, Integer pageNumber) {
        return productRepo.findByName(query, PageRequest.of(pageNumber, pageSize));
    }
}
