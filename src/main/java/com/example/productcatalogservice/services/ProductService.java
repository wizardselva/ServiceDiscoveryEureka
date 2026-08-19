package com.example.productcatalogservice.services;

import com.example.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService implements IProductService {

    @Autowired
    //private RestTemplate restTemplate;
    RestTemplateBuilder restTemplateBuilder;

    @Override
    public Product getProductDetailsById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        Product product=restTemplate.getForObject("https://fakestoreapi.com/products/" + id, Product.class);
        return product;
    }
}
