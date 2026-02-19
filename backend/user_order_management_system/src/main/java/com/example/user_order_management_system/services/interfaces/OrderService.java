package com.example.user_order_management_system.services.interfaces;

import com.example.user_order_management_system.DTO.CreateOrderRequest;
import com.example.user_order_management_system.DTO.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO createOrder(CreateOrderRequest request);

    List<OrderDTO> getCurrentUserOrders();

    OrderDTO getOrderById(Long id);

    void deleteOrder(Long id);
}
