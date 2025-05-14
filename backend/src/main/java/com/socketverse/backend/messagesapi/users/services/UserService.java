package com.socketverse.backend.messagesapi.users.services;

import com.socketverse.backend.messagesapi.chats.models.ChatRepository;
import com.socketverse.backend.messagesapi.users.data.UserEntity;
import com.socketverse.backend.messagesapi.users.models.UserRepository;
import org.openapitools.model.Chat;
import org.openapitools.model.User;
import org.openapitools.model.WebsocketSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;

    @Override
    public WebsocketSession createUser(User user) {
        repository.save(new UserEntity(user));
        var session = new WebsocketSession();
        session.setSession(UUID.randomUUID().toString());
        return session;
    }

    @Override
    public UserEntity getUser(String email) {
        return repository.findUserByEmail(email).orElseThrow();
    }

    @Override
    public void updateUser(String email, User user) {
        UserEntity u = repository.findUserByEmail(email).orElseThrow();
        u.setUsername(user.getUsername());
        u.setAvatarUrl(user.getAvatarUrl());
        repository.save(u);
    }
}
