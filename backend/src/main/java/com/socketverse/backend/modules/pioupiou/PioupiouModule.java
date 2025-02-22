package com.socketverse.backend.modules.pioupiou;

import com.socketverse.backend.modules.GameModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Component
public class PioupiouModule implements GameModule {

    @Override
    public String getGameName() {
        return "\uD83D\uDC23 Piou piou";
    }

    @Override
    public String getGameId() {
        return "pioupiou";
    }

    @Override
    public void start() {
        System.out.println(getGameName()+" is running...");
    }

    @Override
    public void handleGameMessage(WebSocketSession session, String message) throws IOException {
        session.sendMessage(new TextMessage(getGameName()+" received: " + message));
    }
}


