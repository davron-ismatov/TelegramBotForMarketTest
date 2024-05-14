package com.example.telegrambotformarkettest.service.imlp;

import com.example.telegrambotformarkettest.entity.Users;
import com.example.telegrambotformarkettest.enums.UserState;
import com.example.telegrambotformarkettest.repository.UserRepository;
import com.example.telegrambotformarkettest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

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
    public Boolean saveUser(Message message, String field) {
        if (field.equals("FULLNAME")){
            Users users = repository.getUsersByChatId(message.getChatId());
            users.setFull_name(message.getText());
            users.setUserState(UserState.ASK_LOCATION);
            repository.save(users);
            return true;
        } else if (field.equals("CONTACT")) {
            Users users = Users.builder()
                    .chatId(message.getChatId())
                    .phone_number(message.getContact().getPhoneNumber())
                    .userState(UserState.ASK_NAME)
                    .build();
            repository.save(users);
            return true;
        }
        return false;
    }

    @Override
    public Boolean saveUser(Message message, Boolean is_service_available) {
        Users users = repository.getUsersByChatId(message.getChatId());
        users.setLatitude(message.getLocation().getLatitude());
        users.setLongitude(message.getLocation().getLongitude());
        users.setIs_service_available(is_service_available);
        users.setUserState(UserState.ASK_LANGUAGE);
        repository.save(users);
        return true;

    }

    @Override
    public Boolean saveUser(Long chatId, String language) {
        Users users = repository.getUsersByChatId(chatId);
        users.setLanguage(language);
        users.setUserState(UserState.DONE);
        repository.save(users);
        return true;
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
