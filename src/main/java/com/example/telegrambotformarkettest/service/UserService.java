package com.example.telegrambotformarkettest.service;

import com.example.telegrambotformarkettest.entity.Users;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public interface UserService {

    Users getUserByChatID(Long id);

    List<Users> getAllUsers();

    Boolean saveUser(Message message, String field);
    Boolean saveUser(Message message,Boolean is_service_available);
    Boolean saveUser(Long chatId,String language);

    Boolean updateUser(Users users);

    Boolean existsByChat_id(Long chat_id);
}
