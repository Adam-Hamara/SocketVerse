package com.socketverse.backend.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameHelper {

    public List<Map<String, String>> rooms = new ArrayList<>();

    public void addRoom(GameModule gameModule){
        Map<String, String> map = new HashMap<>();
        map.put("gameId", gameModule.getGameId());
        map.put("gameName", gameModule.getGameName());

        rooms.add(map);
    }

    public String toJsonString(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(rooms);
            return jsonString;
        } catch (JsonProcessingException e) {
            System.out.println(e);
            return null;
        }
    }
}
