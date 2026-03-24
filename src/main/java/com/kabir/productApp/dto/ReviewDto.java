package com.kabir.productApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private Long id;
    private Long productId;
    private Long userId;
    private String userName;
    private String comment;
    private Float rating;
    private Date createdAt;
    private Date updatedAt;
}
