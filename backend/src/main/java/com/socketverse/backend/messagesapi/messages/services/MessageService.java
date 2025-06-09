package com.socketverse.backend.messagesapi.messages.services;

import com.socketverse.backend.messagesapi.chats.data.ChatEntity;
import com.socketverse.backend.messagesapi.chats.models.ChatRepository;
import com.socketverse.backend.messagesapi.messages.data.MessageEntity;
import com.socketverse.backend.messagesapi.messages.models.MessageRepository;
import com.socketverse.backend.messagesapi.users.data.UserEntity;
import com.socketverse.backend.messagesapi.users.models.UserRepository;
import org.openapitools.model.Chat;
import org.openapitools.model.Message;
import org.openapitools.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService implements IMessageService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void createMessage(String chatUniqueId, String userUniqueId, Message message) {
        ChatEntity chat = chatRepository.findByUniqueId(chatUniqueId).orElseThrow();
        UserEntity user = userRepository.findByUniqueId(userUniqueId).orElseThrow();
        messageRepository.save(new MessageEntity(message, chat, user));
    }

    @Override
    public List<Message> getMessages(String chatId) {
        return messageRepository.getAllByChatId(chatId);
    }

    @Override
    public void updateMessage(String chatId, Message message) {
        MessageEntity m = messageRepository.findById(chatId).orElseThrow();
        m.setContent(message.getContent());
        m.setStatus(message.getStatus());
        m.setMediaUrl(message.getMediaUrl().get());
        messageRepository.save(m);
    }
}
