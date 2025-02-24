package com.socketverse.backend.modules.magic8ball;

import com.socketverse.backend.modules.IGameModule;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Component
public class Magic8ballModule implements IGameModule {

    @Override
    public String getGameName() {
        return "\uD83C\uDFB1 Magic 8-Ball";
    }

    @Override
    public String getGameId() {
        return "magic8ball";
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

