package com.example.telegrambotformarkettest.service;

import com.example.telegrambotformarkettest.entity.Order;
import com.example.telegrambotformarkettest.entity.Users;
import com.example.telegrambotformarkettest.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    List<Order> getOrderListByUser(Users user);
    Order createOrder(Users user, String orderNumber);
    Order changeOrderStatusById(Long orderId, OrderStatus status);
    void deleteOrderById(Long id);
    boolean existsByUser(Users user);
}