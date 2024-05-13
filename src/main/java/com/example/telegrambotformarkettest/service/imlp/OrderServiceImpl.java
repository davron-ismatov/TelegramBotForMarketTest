package com.example.telegrambotformarkettest.service.imlp;

import com.example.telegrambotformarkettest.entity.Order;
import com.example.telegrambotformarkettest.entity.Users;
import com.example.telegrambotformarkettest.enums.OrderStatus;
import com.example.telegrambotformarkettest.repository.OrderRepository;
import com.example.telegrambotformarkettest.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public List<Order> getOrderListByUser(Users user) {
        return orderRepository.findAllByUsers(user);
    }

    @Override
    public Order createOrder(Users user, String orderNumber) {
        Order order = new Order();
        order.setUsers(user);
        order.setOrderNumber(orderNumber);
        return orderRepository.save(order);
    }

    @Override
    public Order changeOrderStatusById(Long orderId, OrderStatus status) {
        Order order = orderRepository.getReferenceById(orderId);
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public boolean existsByUser(Users user) {
        return orderRepository.existsByUsers(user);
    }
}