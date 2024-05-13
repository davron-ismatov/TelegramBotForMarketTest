package com.example.telegrambotformarkettest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users_tbl")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String full_name;
    private String phone_number;
    private Double longitude;
    private Double latitude;
    private String language;
    private Boolean is_service_available;
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    List<Order> orderList;
}
