package com.example.telegrambotformarkettest.service;

import com.example.telegrambotformarkettest.entity.Users;
import org.telegram.telegrambots.meta.api.objects.Location;

import java.util.List;

public interface UserService {

    Users getUserByChatID(Long id);

    List<Users> getAllUsers();

    Boolean saveUser(String text,String field);
    Boolean saveUser(Location location,Boolean is_service_available);

    Boolean updateUser(Users users);

    Boolean existsByChat_id(Long chat_id);
}
