package com.vinicius_almeida.mancala.game;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameDb implements IGame {

    private static final Map<UUID, IGame> gameIdMap = new HashMap<>();

    private final IGame game;

    public GameDb(IGame game) {
        this.game = game;
        gameIdMap.putIfAbsent(game.getId(), game);
    }

    public GameDb(UUID gameId) {
        this(gameIdMap.get(gameId));
    }

    @Override
    public UUID getId() {
        return game.getId();
    }

    @Override
    public GameStatus getStatus() {
        return game.getStatus();
    }

    @Override
    public GameStatus move(Player player, int position) {
        return game.move(player, position);
    }
}
