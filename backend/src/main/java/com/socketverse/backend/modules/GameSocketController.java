package com.socketverse.backend.modules;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GameSocketController {
    private final Map<String, IGameModule> activeGames = new HashMap<>();

    public GameSocketController(List<IGameModule> games) {
        for (IGameModule game : games) {
            activeGames.put(game.getGameId(), game);
        }
    }

    public IGameModule getGame(String gameId) {
        return activeGames.get(gameId);
    }
}

