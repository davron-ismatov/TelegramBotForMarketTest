package com.example.telegrambotformarkettest.entity;

import com.example.telegrambotformarkettest.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders_tbl")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    Users users;
    @Column(name = "order_number", nullable = false)
    String orderNumber;
    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus = OrderStatus.CREATED;
    @Column(name = "total_sum")
    Float totalSum = 0f;
    @Column(name = "created_time", nullable = false, updatable = false)
    @CreationTimestamp
    Timestamp timestamp;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderItem> orderItemList;
}