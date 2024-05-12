package com.example.telegrambotformarkettest.botService;

import com.example.telegrambotformarkettest.service.UserService;
import com.example.telegrambotformarkettest.utils.MessageUtils;
import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot{


    @Value(value = "${botToken}")
    public  String botToken;
    @Value(value = "${botUsername}")
    public  String botUsername;
    private final MessageUtils messageUtils;
    private final UserService userService;
    private  static String lastBotMessage = "";
    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
//    private void TODO(){
//        // todo: add UserState to database
//        // todo: work with States while editing User's info
//    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message!=null){
            if (message.hasText()){

               if (message.getText().equals("/start")){
                   sendContact(messageUtils.generateMessage(update,"Your contact: "));
               }

                if (Objects.equals(lastBotMessage, "Your full name: ")){
                    userService.saveUser(message.getText(),"FULLNAME");
                    sendLocation(messageUtils.generateMessage(update, "Your location: "));
                }


            } else if (message.hasContact()) {
                   lastBotMessage = "Your full name: ";
                   executeMessage(
                           messageUtils.generateMessage(update, "Your full name: ")
                   );
            }

        }
    }


    private void sendLocation(SendMessage sendMessage) {
        List<KeyboardRow> list = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add(EmojiParser.parseToUnicode(":round_pushpin:") + "Location");
        row.get(0).setRequestLocation(true);
        list.add(row);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(list);
        markup.setSelective(true);
        markup.setResizeKeyboard(true);

        sendMessage.setReplyMarkup(markup);

        executeMessage(sendMessage);
    }
    
    

    private void sendContact(SendMessage sendMessage){
        List<KeyboardRow> list = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add(EmojiParser.parseToUnicode(":telephone_receiver")+"Contact");
        row.get(0).setRequestContact(true);
        list.add(row);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        markup.setKeyboard(list);
        sendMessage.setReplyMarkup(markup);
        executeMessage(sendMessage);
    }

    private void executeMessage(SendMessage sendMessage){
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
