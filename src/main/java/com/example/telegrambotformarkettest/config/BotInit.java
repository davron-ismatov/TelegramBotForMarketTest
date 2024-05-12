package com.example.telegrambotformarkettest.config;

import com.example.telegrambotformarkettest.botService.TelegramBot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@RequiredArgsConstructor
@Slf4j
public class BotInit {
    private final TelegramBot bot;

    @EventListener({ContextRefreshedEvent.class})
    public void initialize(){
        try {
            log.info(bot.getBotToken());
            log.info(bot.getBotUsername());
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(bot);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
