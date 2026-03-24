package com.kabir.productApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteDto {
    private Long id;
    private Long productId;
    private String productTitle;
    private Long userId;
}
