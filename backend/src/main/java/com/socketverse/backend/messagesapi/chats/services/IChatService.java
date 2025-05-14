package com.socketverse.backend.messagesapi.chats.services;

import com.socketverse.backend.messagesapi.chats.data.ChatEntity;
import org.openapitools.model.Chat;

import java.util.List;

public interface IChatService {
    void createNewChat(Chat chat);
    List<ChatEntity> getAllChats(String userId);
    void updateChat(String chatId, Chat chat);
}
