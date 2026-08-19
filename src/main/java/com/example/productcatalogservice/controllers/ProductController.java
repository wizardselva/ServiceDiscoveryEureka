package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.FakeStoreProductDto;
import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//@Controller @ResponseBody
@RestController
public class ProductController {

    @Autowired
    private IProductService productService;
//    public ProductController(IProductService productService) {
//        this.productService = productService;
//    }

    @GetMapping
    public ProductDto getProductDetailsBy(@PathVariable("id") Long productId){
//        ProductDto productDto = new ProductDto();
//        productDto.setId(productId);
//        productDto.setName("Wizard Phone");
//        return productDto;
        Product product = productService.getProductDetailsById(productId);
        return null;
    }

    @GetMapping("products/{id}")
    public Product getProductDetailsById(@PathVariable("id") Long productId){
//        ProductDto productDto = new ProductDto();
//        productDto.setId(productId);
//        productDto.setName("Wizard Phone");
//        return productDto;
        Product product = productService.getProductDetailsById(productId);
        return product;
    }
}
