package com.example.user_order_management_system.repository;

import com.example.user_order_management_system.entity.Order;
import com.example.user_order_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
}
