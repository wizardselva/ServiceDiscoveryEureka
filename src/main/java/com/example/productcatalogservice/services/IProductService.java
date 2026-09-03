package com.example.productcatalogservice.services;

import com.example.productcatalogservice.models.Product;

public interface IProductService {
    Product getProductDetailsById(Long id);

    Product replaceProduct(Long id, Product inputProduct);

    Product createProduct(Product product);
}
