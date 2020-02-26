package com.vinicius_almeida.mancala.game;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class GameLocalRepository implements IGameRepository {

    private static final Map<UUID, Game> gameIdMap = new HashMap<>();

    @Override
    public Game save(Game game) {
        UUID id;
        if (game.getId() != null) {
            id = game.getId();
        } else {
            id = UUID.randomUUID();
        }

        game.setId(id);
        gameIdMap.put(id, game);
        return game;
    }

    @Override
    public Optional<Game> findById(UUID id) {
        var game = gameIdMap.get(id);

        if (game != null) {
            return Optional.of(game);
        }

        return Optional.empty();
    }

}
