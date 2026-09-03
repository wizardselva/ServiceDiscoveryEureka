package com.example.productcatalogservice.services;

import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
//@Primary
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product getProductDetailsById(Long id) {
        Optional<Product> optionalProduct=productRepo.findById(id);
        if(optionalProduct.isPresent())
            return optionalProduct.get();
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product inputProduct) {
        Optional<Product> optionalProduct=productRepo.findById(inputProduct.getId());
        if(optionalProduct.isPresent())
            throw new RuntimeException("Product with Id "+inputProduct.getId()+" aleady exists");
        inputProduct.setId(id);
        inputProduct.setLastUpdatedAt(new Date());
        return productRepo.save(inputProduct);
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Product> optionalProduct=productRepo.findById(product.getId());
        if(optionalProduct.isPresent())
            throw new RuntimeException("Product with Id "+product.getId()+" aleady exists");
        return productRepo.save(product);
    }

    //GetAllProducts -> findAll()  ---> (H.W)
    //deleteProduct  -> deleteById(id)
    //updateProduct  -> save()
}
