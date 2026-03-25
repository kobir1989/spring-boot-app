package com.kabir.productApp.service;

import com.kabir.productApp.dto.ProductDto;
import com.kabir.productApp.entity.CategoryEntity;
import com.kabir.productApp.entity.ProductEntity;
import com.kabir.productApp.exception.ResourceNotFoundException;
import com.kabir.productApp.repository.CategoryRepository;
import com.kabir.productApp.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductDto addProduct( ProductDto productDto ) {

        CategoryEntity category = categoryRepository
                .findById(productDto.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Category not found"));

        ProductEntity product = ProductEntity.builder()
                .title(productDto.getTitle())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .category(category)
                .isActive(productDto.getIsActive())
                .build();
        ProductEntity saved =  productRepository.save(product);

        // Map Entity To DTO
        return ProductDto.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .description(saved.getDescription())
                .price(saved.getPrice())
                .isActive(saved.getIsActive())
                .categoryId(saved.getCategory().getId())
                .createdAt(saved.getCreatedAt())
                .updatedAt(saved.getUpdatedAt())
                .build();
    }
}
