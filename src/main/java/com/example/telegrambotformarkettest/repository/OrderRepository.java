package com.example.telegrambotformarkettest.repository;

import com.example.telegrambotformarkettest.entity.Order;
import com.example.telegrambotformarkettest.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUsers(Users users);

    boolean existsByUsers(Users users);
}