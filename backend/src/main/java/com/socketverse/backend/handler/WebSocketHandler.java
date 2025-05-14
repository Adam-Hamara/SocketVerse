package com.socketverse.backend.handler;

import com.socketverse.backend.handler.request.*;
import com.socketverse.backend.modules.GameLoader;
import com.socketverse.backend.modules.IGameModule;
import com.socketverse.backend.modules.GameHelper;
import com.socketverse.backend.modules.IGameRoom;
import com.socketverse.backend.modules.pioupiou.PioupiouRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private static final Logger log = LogManager.getLogger(WebSocketHandler.class);
    private final Map<String, IGameModule> activeGames;  // Active game modules
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    private final Map<String, IGameRoom> activeRooms = new HashMap<>();

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
        PayloadRequest request = new PayloadRequest(payload);
        try {

            switch(request.payloadType){
                case PayloadType.DATA -> handlePlayerAction(request.payloadRequest, session);
                case PayloadType.STRING -> handlePageAction(request.payloadRequest, session);
                case PayloadType.ERROR -> session.sendMessage(new TextMessage("Error."));
                case PayloadType.OTHER -> session.sendMessage(new TextMessage("Other."));
                case PayloadType.UNKNOWN -> session.sendMessage(new TextMessage("Unknown."));
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

    public void handlePlayerAction(Request request, WebSocketSession session) throws IOException {
        String gameId = (String) session.getAttributes().get("gameId");
        String action = request.payload.action;
        if (gameId != null && activeGames.containsKey(gameId)) {
            activeGames.get(gameId).handleGameMessage(session, action);
        } else {
            session.sendMessage(new TextMessage("You must join a game first using: /join {gameName}"));
        }
    }

    public void handlePageAction(Request request, WebSocketSession session) throws IOException {
        String payload = request.payload.message;
        if (payload.startsWith("/list_games")) {
            session.sendMessage(new TextMessage( GameHelper.activeGamesToJson(activeGames) ));
        }
        else if (payload.startsWith("/list_rooms")) {
            session.sendMessage(new TextMessage( GameHelper.activeRoomsToJson(activeRooms) ));
        }
        else if (payload.startsWith("/join_room")) {
            String gameId = GameHelper.readString(payload);
            if(gameId != null){
                var roomUuid = gameId.split(" ")[1];
                var name = gameId.split(" ")[2];

                if (activeRooms.containsKey(roomUuid)) {
                    IGameRoom room = activeRooms.get(roomUuid);
                    room.addPlayer(name, session);
                    session.sendMessage(new TextMessage("Joined " + roomUuid));
                } else {
                    session.sendMessage(new TextMessage("Room not available."));
                }
            }
            else{
                session.sendMessage(new TextMessage("Error incorrect format for: " + payload));
            }
        }
        else if (payload.startsWith("/create_room")) {
            String unpackedPayload = GameHelper.readString(payload);
            if(unpackedPayload != null){
                var gameId = unpackedPayload.split(" ")[1];
                var name = unpackedPayload.split(" ")[2];

                if (activeGames.containsKey(gameId)) {
                    String uuid = UUID.randomUUID().toString();
                    activeRooms.put(uuid, GameHelper.resolveGameId(gameId));
                    IGameRoom room = activeRooms.get(uuid);
                    room.addPlayer(name, session);
                    session.sendMessage(new TextMessage("Created room " + uuid));
                } else {
                    session.sendMessage(new TextMessage("Game not available."));
                }
            }
            else{
                session.sendMessage(new TextMessage("Error incorrect format for: " + payload));
            }
        }
    }
}


