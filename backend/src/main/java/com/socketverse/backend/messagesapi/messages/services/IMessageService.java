package com.socketverse.backend.messagesapi.messages.services;

import org.openapitools.model.Message;
import org.openapitools.model.User;

import java.util.List;

public interface IMessageService {
    void createMessage(String chatId, String userId, Message message);
    List<Message> getMessages(String chatId);
    void updateMessage(String chatId, Message message);
}
