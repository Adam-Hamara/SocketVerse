package com.socketverse.backend.modules;

import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;

public interface GameModule {
    String getGameName();
    String getGameId();
    void start();
    void handleGameMessage(WebSocketSession session, String message) throws IOException;
}


