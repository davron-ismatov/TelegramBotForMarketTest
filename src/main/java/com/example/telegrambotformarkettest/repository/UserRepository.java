package com.example.telegrambotformarkettest.repository;

import com.example.telegrambotformarkettest.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Users getUsersByChatId(Long chatId);

    boolean existsByChatId(Long chat_id);
}
