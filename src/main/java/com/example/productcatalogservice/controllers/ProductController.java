package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.CategoryDto;
import com.example.productcatalogservice.dtos.FakeStoreProductDto;
import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller @ResponseBody
@RestController
public class ProductController {

    @Autowired
    @Qualifier("storageProductService")
    private IProductService productService;
//    public ProductController(IProductService productService) {
//        this.productService = productService;
//    }

//    @GetMapping("products/{id}")
//    public ProductDto getProductDetailsBy(@PathVariable("id") Long productId){
////        ProductDto productDto = new ProductDto();
////        productDto.setId(productId);
////        productDto.setName("Wizard Phone");
////        return productDto;
//        Product product = productService.getProductDetailsById(productId);
//        return null;
//    }

//    @GetMapping("products/{id}")
//    public Product getProductDetailsById(@PathVariable("id") Long productId){
////        ProductDto productDto = new ProductDto();
////        productDto.setId(productId);
////        productDto.setName("Wizard Phone");
////        return productDto;
//        Product product = productService.getProductDetailsById(productId);
//        return product;
//    }

    @GetMapping("products/{id}")
    public ResponseEntity<ProductDto> getProductDetailsById(@PathVariable("id") Long productId) throws IllegalAccessException {
    //        ProductDto productDto = new ProductDto();
    //        productDto.setId(productId);
    //        productDto.setName("Wizard Phone");
    //        return productDto;
        if(productId<=0L){
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            throw new IllegalArgumentException("Pls pass id > 0");
        }

        Product product = productService.getProductDetailsById(productId);
        if(product==null){
//            return new  ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            throw new RuntimeException("Product not found");
        }
        ProductDto productDto= from(product);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product product=from(productDto);
        Product output=productService.createProduct(product);
        ProductDto resp=from(output);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto> replaceProduct(@PathVariable Long id,
                                                     @RequestBody ProductDto productDto) {
        //validation -> map to ProductEntity -> save -> map to Product -> return
        Product inputProduct = from(productDto);
        //inputProduct.setId(id);
        Product output = productService.replaceProduct(id, inputProduct);
        ProductDto responseProductDto = from(output);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());

        if (productDto.getCategory() != null) {
            Category category = new Category();
            category.setName(productDto.getCategory().getName());
            category.setId(productDto.getCategory().getId());
            product.setCategory(category);
        }

        return product;
    }

    private ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());

        if (product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setDescription(product.getCategory().getDescription());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }
}
