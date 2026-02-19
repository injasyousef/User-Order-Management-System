package com.example.user_order_management_system.DTO;

import lombok.Data;

@Data
public class CreateOrderRequest {

    private String productName;
    private Double price;
    private Integer quantity;
}
