package com.socketverse.backend.messagesapi.users.services;

import com.socketverse.backend.advice.BadRequestException;
import com.socketverse.backend.messagesapi.chats.models.ChatRepository;
import com.socketverse.backend.messagesapi.users.data.UserEntity;
import com.socketverse.backend.messagesapi.users.models.UserRepository;
import org.openapitools.model.Chat;
import org.openapitools.model.User;
import org.openapitools.model.WebsocketSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        return repository.findByEmail(email).orElse(null);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        Iterable<UserEntity> iterable = repository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    @Override
    public void updateUser(User user) {
        UserEntity u = repository.findByUniqueId(user.getUniqueId()).orElseThrow();
        u.setUsername(user.getUsername());
        u.setAvatarUrl(user.getAvatarUrl());
        repository.save(u);
    }
}
