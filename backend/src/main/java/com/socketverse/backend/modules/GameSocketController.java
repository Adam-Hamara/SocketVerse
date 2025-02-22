package com.socketverse.backend.modules;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GameSocketController {
    private final Map<String, GameModule> activeGames = new HashMap<>();

    public GameSocketController(List<GameModule> games) {
        for (GameModule game : games) {
            activeGames.put(game.getGameId(), game);
        }
    }

    public GameModule getGame(String gameId) {
        return activeGames.get(gameId);
    }
}

