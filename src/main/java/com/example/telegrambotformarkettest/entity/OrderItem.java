package com.example.telegrambotformarkettest.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items_tbl")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    Long id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    Order order;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    Product product;
    @Column(name = "amount", nullable = false)
    Float amount;
}