package com.example.telegrambotformarkettest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity @Table(name = "category_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Date createdTime;
}
