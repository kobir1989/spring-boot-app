package com.kabir.productApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private Boolean isActive;
    private Long categoryId;
    private String categoryName;
    private Date createdAt;
    private Date updatedAt;
}
