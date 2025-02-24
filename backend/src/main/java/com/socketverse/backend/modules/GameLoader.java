package com.socketverse.backend.modules;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class GameLoader {
    @Value("${selected.games}")
    private String enabledGames;

    private final Map<String, IGameModule> activeGames = new HashMap<>();
    private final List<IGameModule> allGames;

    public GameLoader(List<IGameModule> allGames) {
        this.allGames = allGames;
    }

    @Bean
    public Map<String, IGameModule> activeGames() {
        List<String> enabledGameIds = List.of(enabledGames.split(","));

        for(var gameEnabled: allGames.stream().filter(game -> enabledGameIds.contains(game.getGameId())).toList()){
            activeGames.put(gameEnabled.getGameName(), gameEnabled);
        }
        return activeGames;
    }
}


