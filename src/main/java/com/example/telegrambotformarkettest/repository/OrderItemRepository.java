package com.example.telegrambotformarkettest.repository;

import com.example.telegrambotformarkettest.entity.Order;
import com.example.telegrambotformarkettest.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrder(Order order);
    boolean existsByOrder(Order order);
}