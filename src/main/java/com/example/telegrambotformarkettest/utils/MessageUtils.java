package com.example.telegrambotformarkettest.utils;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class MessageUtils {

    public SendMessage generateMessage(Update update,String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        return sendMessage;
    }
}
