package com.example.telegrambotformarkettest.service.imlp;

import com.example.telegrambotformarkettest.entity.Users;
import com.example.telegrambotformarkettest.repository.UserRepository;
import com.example.telegrambotformarkettest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Location;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    @Override
    public Users getUserByChatID(Long chat_id) {
        return repository.getUsersByChatId(chat_id);
    }

    @Override
    public List<Users> getAllUsers() {
        return null;
    }

    @Override
    public Boolean saveUser(String text, String field) {
        log.info(text);
        return false;
    }

    @Override
    public Boolean saveUser(Location location, Boolean is_service_available) {
        return false;
    }

    @Override
    public Boolean updateUser(Users users) {
        return null;
    }

    @Override
    public Boolean existsByChat_id(Long chat_id) {
        return repository.existsByChatId(chat_id);
    }
}
