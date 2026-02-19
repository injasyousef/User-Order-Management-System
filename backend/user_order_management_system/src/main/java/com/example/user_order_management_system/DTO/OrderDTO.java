package com.example.user_order_management_system.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderDTO {

    private Long id;
    private String productName;
    private Double price;
    private Integer quantity;
    private LocalDateTime orderDate;
}
