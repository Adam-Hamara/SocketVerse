package com.socketverse.backend.messagesapi.users.services;

import com.socketverse.backend.messagesapi.users.data.UserEntity;
import org.openapitools.model.User;
import org.openapitools.model.WebsocketSession;

import java.util.List;

public interface IUserService {
    WebsocketSession createUser(User user);
    UserEntity getUser(String userId);
    void updateUser(String userId, User user);
}
