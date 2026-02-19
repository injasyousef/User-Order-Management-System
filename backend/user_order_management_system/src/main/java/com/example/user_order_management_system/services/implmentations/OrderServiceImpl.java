package com.example.user_order_management_system.services.implmentations;

import com.example.user_order_management_system.DTO.CreateOrderRequest;
import com.example.user_order_management_system.DTO.OrderDTO;
import com.example.user_order_management_system.entity.Order;
import com.example.user_order_management_system.entity.User;
import com.example.user_order_management_system.exceptions.ResourceNotFoundException;
import com.example.user_order_management_system.repository.OrderRepository;
import com.example.user_order_management_system.repository.UserRepository;
import com.example.user_order_management_system.services.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public OrderDTO createOrder(CreateOrderRequest request) {

        User user = getCurrentUser();

        Order order = Order.builder()
                .productName(request.getProductName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .orderDate(LocalDateTime.now())
                .user(user)
                .build();

        Order saved = orderRepository.save(order);

        return mapToDTO(saved);
    }

    @Override
    public List<OrderDTO> getCurrentUserOrders() {
        User user = getCurrentUser();

        return orderRepository.findByUser(user)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        return mapToDTO(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO mapToDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .productName(order.getProductName())
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .orderDate(order.getOrderDate())
                .build();
    }
}
