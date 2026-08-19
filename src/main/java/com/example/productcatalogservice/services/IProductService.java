package com.example.productcatalogservice.services;

import com.example.productcatalogservice.models.Product;

public interface IProductService {
    Product getProductDetailsById(Long id);
}
