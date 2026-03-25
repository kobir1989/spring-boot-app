package com.kabir.productApp.controller;

import com.kabir.productApp.dto.ProductDto;
import com.kabir.productApp.entity.ProductEntity;
import com.kabir.productApp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product/add")
    public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody ProductDto productDto) {
       ProductDto saved = productService.addProduct(productDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
