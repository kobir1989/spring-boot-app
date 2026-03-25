package com.kabir.productApp.service;

import com.kabir.productApp.dto.CategoryDto;
import com.kabir.productApp.entity.CategoryEntity;
import com.kabir.productApp.exception.BadRequestException;
import com.kabir.productApp.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto addCategory(CategoryDto categoryDto) {

        if (categoryRepository.existsByName(categoryDto.getName())) {
            throw new BadRequestException("Category with name " + categoryDto.getName() + " already exists");
        }

       CategoryEntity category = CategoryEntity
               .builder()
               .name(categoryDto.getName())
               .build();

       CategoryEntity savedCategory = categoryRepository.save(category);

       return  CategoryDto
               .builder()
               .id(savedCategory.getId())
               .name(savedCategory.getName())
               .createdAt(savedCategory.getCreatedAt())
               .updatedAt(savedCategory.getUpdatedAt())
               .build();

    }
}
