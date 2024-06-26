package com.example.telegrambotformarkettest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "product_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    private Category category;

    private Float price;

    private String photoPath;

    private Integer totalAmount;

    private Integer soldAmount;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<OrderItem> orderItemList;
}
