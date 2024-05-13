package com.example.telegrambotformarkettest.service;

import com.example.telegrambotformarkettest.entity.Order;
import com.example.telegrambotformarkettest.entity.OrderItem;
import com.example.telegrambotformarkettest.entity.Product;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> getOrderItemListByOrder(Order order);
    OrderItem createOrderItem(Order order, Product product, Float amount);
    OrderItem updateOrderItemById(Long id, Product product, Float amount);
    boolean existsByOrder(Order order);
    void deleteOrderItemById(Long id);
}