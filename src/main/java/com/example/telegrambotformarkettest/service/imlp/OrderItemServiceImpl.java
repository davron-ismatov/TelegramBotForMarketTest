package com.example.telegrambotformarkettest.service.imlp;

import com.example.telegrambotformarkettest.entity.Order;
import com.example.telegrambotformarkettest.entity.OrderItem;
import com.example.telegrambotformarkettest.entity.Product;
import com.example.telegrambotformarkettest.repository.OrderItemRepository;
import com.example.telegrambotformarkettest.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> getOrderItemListByOrder(Order order) {
        return orderItemRepository.findAllByOrder(order);
    }

    @Override
    public OrderItem createOrderItem(Order order, Product product, Float amount) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setAmount(amount);
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem updateOrderItemById(Long id, Product product, Float amount) {
        OrderItem orderItem = orderItemRepository.getReferenceById(id);
        orderItem.setProduct(product);
        orderItem.setAmount(amount);
        return orderItemRepository.save(orderItem);
    }

    @Override
    public boolean existsByOrder(Order order) {
        return orderItemRepository.existsByOrder(order);
    }

    @Override
    public void deleteOrderItemById(Long id) {
        orderItemRepository.deleteById(id);
    }
}