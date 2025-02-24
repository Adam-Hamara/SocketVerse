package com.socketverse.backend.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socketverse.backend.modules.magic8ball.Magic8ballModule;
import com.socketverse.backend.modules.pioupiou.PioupiouRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameHelper {

    private static final Logger log = LogManager.getLogger(GameHelper.class);

    public static String activeGamesToJson(Map<String, IGameModule> activeGames){
        try {
            List<Map<String, String>> games = new ArrayList<>();
            for(var gameId : activeGames.keySet()){
                Map<String, String> map = new HashMap<>();
                IGameModule module = activeGames.get(gameId);
                map.put("gameId", module.getGameId());
                map.put("gameName", module.getGameName());
                games.add(map);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(games);
        } catch (JsonProcessingException e) {
            log.error("e: ", e);
            return "JsonProcessingException...";
        }
    }

    public static String activeRoomsToJson(Map<String, IGameRoom> activeRooms){
        try {
            List<Map<String, String>> rooms = new ArrayList<>();
            for(var roomUuid : activeRooms.keySet()){
                Map<String, String> map = new HashMap<>();
                IGameRoom room = activeRooms.get(roomUuid);
                map.put("roomUuid", roomUuid);
                map.put("roomName", room.getName());
                rooms.add(map);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(rooms);
        } catch (JsonProcessingException e) {
            log.error("e: ", e);
            return "JsonProcessingException...";
        }
    }

    public static String readString(String jsonPayload){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.reader().readValue(jsonPayload, String.class);
        } catch (IOException e) {
            return null;
        }
    }

    public static IGameRoom resolveGameId(String gameId){
        return switch(gameId){
            case "pioupiou" -> new PioupiouRoom();
            default -> throw new IllegalStateException("Unexpected value: " + gameId);
        };
    }
}
