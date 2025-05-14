package com.socketverse.backend.messagesapi.chats.services;

import com.socketverse.backend.messagesapi.chats.data.ChatEntity;
import com.socketverse.backend.messagesapi.chats.models.ChatRepository;
import com.socketverse.backend.messagesapi.users.data.UserEntity;
import com.socketverse.backend.messagesapi.users.models.UserRepository;
import org.openapitools.model.Chat;
import org.openapitools.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService implements IChatService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public void createNewChat(Chat chat) {
        List<UserEntity> users = new ArrayList<>();
        for(User user: chat.getParticipants()){
            users.add(userRepository.findUserByEmail(user.getEmail()).orElseThrow());
        }
        ChatEntity c = new ChatEntity(chat, users);
        chatRepository.save(c);
    }

    @Override
    public List<ChatEntity> getAllChats(String userId) {
        return chatRepository.getAllChatsForUser(userId).orElseThrow();
    }

    @Override
    public void updateChat(String chatId, Chat chat) {
        ChatEntity c = chatRepository.findByChatId(chatId).orElseThrow();
//        c.set(chat.getName());
//        c.setParticipants(chat.getParticipants());
        chatRepository.save(c);
    }
}
