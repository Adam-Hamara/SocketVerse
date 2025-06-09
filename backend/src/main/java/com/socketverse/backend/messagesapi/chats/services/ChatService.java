package com.socketverse.backend.messagesapi.chats.services;

import com.socketverse.backend.advice.NotFoundException;
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
        User u1 = chat.getParticipants().get(0);
        User u2 = chat.getParticipants().get(1);
        List<ChatEntity> commonChats = chatRepository.findChatsByParticipants(u1.getUniqueId(), u2.getUniqueId());
        if(commonChats.stream().anyMatch((c) -> c.getType() == Chat.TypeEnum.DIRECT)) {
            // Chat already exists
            return;
        }
        List<UserEntity> users = new ArrayList<>();
        for (User user : chat.getParticipants()) {
            users.add(userRepository.findByUniqueId(user.getUniqueId()).orElseThrow());
        }
        ChatEntity c = new ChatEntity(chat, users);
        chatRepository.save(c);
    }

    @Override
    public List<ChatEntity> getAllChats(String uniqueId) {
        return chatRepository.getAllChatsForUser(uniqueId).orElseThrow();
    }

    @Override
    public void updateChat(Chat chat) {
        ChatEntity c = chatRepository.findByUniqueId(chat.getUniqueId()).orElseThrow();
//        c.set(chat.getName());
//        c.setParticipants(chat.getParticipants());
        chatRepository.save(c);
    }
}
