package com.example.telegrambotformarkettest.botService;

import com.example.telegrambotformarkettest.entity.Users;
import com.example.telegrambotformarkettest.enums.UserState;
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
    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message!=null){
            Users users =  userService.getUserByChatID(message.getChatId());
            if (message.hasText()){

               if (message.getText().equals("/start")){
                   sendContact(messageUtils.generateMessage(update,"Your contact: "));
               }

                if (users.getUserState() == UserState.ASK_NAME){
                    userService.saveUser(message,"FULLNAME");
                    sendLocation(messageUtils.generateMessage(update, "Your location: "));
                }


            } else if (message.hasContact()) {
                userService.saveUser(message,"CONTACT");

                   executeMessage(
                           messageUtils.generateMessage(update, "Your full name: ")
                   );
            } else if (message.hasLocation()) {
                boolean serviceAvailable = isServiceAvailable(message.getLocation());
                if (serviceAvailable) {
                    userService.saveUser(message, serviceAvailable);
                    menu(messageUtils.generateMessage(update, "Service is available in your location"));
                }else {
                    userService.saveUser(message, serviceAvailable);
                    menu(messageUtils.generateMessage(update, "Service is unavailable in your location"));
                }
                userService.saveUser(message.getChatId(),message.getFrom().getLanguageCode());
            }

        }
    }

    private void menu(SendMessage sendMessage){
        List<KeyboardRow> list = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("\uD83C\uDFF7 Product");
        KeyboardRow row1 = new KeyboardRow();
        row.add("\uD83C\uDFF7️ Product");
        KeyboardRow row2 = new KeyboardRow();
        row.add("\uD83C\uDFF7️ Product");
        KeyboardRow row3 = new KeyboardRow();
        row.add("\uD83C\uDFF7️ Product");

        list.add(row);
        list.add(row1);
        list.add(row2);
        list.add(row3);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        markup.setKeyboard(list);
        markup.setSelective(true);
        sendMessage.setReplyMarkup(markup);
        executeMessage(sendMessage);

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
        row.add(EmojiParser.parseToUnicode(":telephone_receiver:")+"Contact");
        row.get(0).setRequestContact(true);
        list.add(row);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        markup.setKeyboard(list);
        markup.setOneTimeKeyboard(true);
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

    private boolean isServiceAvailable(Location location){

        return true;
    }
}
