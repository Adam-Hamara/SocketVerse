package com.socketverse.backend.modules;

import org.springframework.web.socket.WebSocketSession;

public interface IGameRoom {
    void addPlayer(String name, WebSocketSession session);
    String getName();
}
