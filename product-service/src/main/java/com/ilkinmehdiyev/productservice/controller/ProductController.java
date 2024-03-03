package com.ilkinmehdiyev.productservice.controller;

import com.github.javafaker.Faker;
import com.ilkinmehdiyev.productservice.controller.response.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @GetMapping("/getProducts")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        Faker faker = new Faker();
        int rand = new Random().nextInt(0, 10);

        List<ProductDto> allProducts = new LinkedList<>();
        ProductDto productDto;
        for (int i = 0; i < rand; i++) {
            productDto = new ProductDto(faker.funnyName().name(), rand, faker.color().name());
            allProducts.add(productDto);
        }

        return ResponseEntity.ok(allProducts);
    }
}
