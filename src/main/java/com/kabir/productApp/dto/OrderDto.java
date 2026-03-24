package com.kabir.productApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Long userId;
    private List<OrderItemDto> orderItems;
    private BigDecimal price;
    private String status;
    private Date createdAt;
    private Date updatedAt;
}
