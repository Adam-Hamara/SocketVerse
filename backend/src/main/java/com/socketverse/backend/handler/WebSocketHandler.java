package com.socketverse.backend.handler;

import com.socketverse.backend.modules.GameLoader;
import com.socketverse.backend.modules.GameModule;
import com.socketverse.backend.modules.GameHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private static final Logger log = LogManager.getLogger(WebSocketHandler.class);
    private final Map<String, GameModule> activeGames;  // Active game modules
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public WebSocketHandler(GameLoader gameLoader) {
        this.activeGames = gameLoader.activeGames(); // Load active games
        this.activeGames.forEach((s, gameModule) -> gameModule.start());
        log.info("Loaded games: {}", this.activeGames.size());
    }

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        sessions.put(session.getId(), session);
//        session.sendMessage(new TextMessage("Connected. Choose a game by sending: /join {gameName}"));
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, TextMessage message)  {
        String payload = message.getPayload();
        try {
            // If the message is a join request, switch game context
            if (payload.startsWith("/join ")) {
                String gameId = payload.substring(6).trim();
                if (activeGames.containsKey(gameId)) {
                    session.sendMessage(new TextMessage("Joined " + gameId));
                    session.getAttributes().put("gameId", gameId); // Store game selection
                } else {
                    session.sendMessage(new TextMessage("Game not available."));
                }
            }
            else if (payload.equals("/rooms")) {
                GameHelper handler = new GameHelper();
                for(var gameId : activeGames.keySet()){
                    handler.addRoom(activeGames.get(gameId));
                }
                session.sendMessage(new TextMessage( handler.toJsonString() ));
            }
            // If already in a game, forward message to the correct game handler
            else {
                String gameId = (String) session.getAttributes().get("gameId");
                if (gameId != null && activeGames.containsKey(gameId)) {
                    activeGames.get(gameId).handleGameMessage(session, payload);
                } else {
                    session.sendMessage(new TextMessage("You must join a game first using: /join {gameName}"));
                }
            }
        }
        catch(IOException e){
            log.error("e: ", e);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session,@NonNull CloseStatus status) {
        sessions.remove(session.getId());
    }
}


