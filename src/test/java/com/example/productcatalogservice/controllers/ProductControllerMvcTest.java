package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void TestGetProductDetailsById_withValidId_RunSuccessfully() throws Exception{

        //Arrange
        Product product= new Product();
        product.setId(2L);
        product.setName("MacBook");
        when(productService.getProductDetailsById(2L))
                .thenReturn(product);

//        mockMvc.perform(get("/products/2"))  //Act
//                .andExpect(status().isInternalServerError()); //Assert

        ProductDto productDto=new ProductDto();
        productDto.setId(2L);
        productDto.setName("MacBook");
        String expectedResponse=objectMapper.writeValueAsString(productDto);

        mockMvc.perform(get("/products/2"))  //Act
                .andExpect(status().isOk()) //Assert
                .andExpect(content().string(expectedResponse));



    }
}
