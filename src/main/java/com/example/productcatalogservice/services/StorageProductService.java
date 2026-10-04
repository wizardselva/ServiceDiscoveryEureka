package com.example.productcatalogservice.services;

import com.example.productcatalogservice.dtos.UserDto;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Optional;

@Service
//@Primary
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private RestTemplate restTemplate;

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

    @Override
    public Product getProductBasedOnUserRole(Long productId, Long userId) {
        Optional<Product> productOptional=productRepo.findById(productId);

        if(productOptional.isEmpty()) return null;

        //We are calling userService using SD...
        ResponseEntity<UserDto> userDtoResponseEntity= restTemplate.getForEntity("http://UserAuthService/users/{userId}", UserDto.class, userId);

        //This conditions tests that response is not null
        if(userDtoResponseEntity.hasBody()){
            System.out.println(userDtoResponseEntity.getBody().getEmail());
            return productOptional.get();
        }

        return null;
    }

    //GetAllProducts -> findAll()  ---> (H.W)
    //deleteProduct  -> deleteById(id)
    //updateProduct  -> save()
}
