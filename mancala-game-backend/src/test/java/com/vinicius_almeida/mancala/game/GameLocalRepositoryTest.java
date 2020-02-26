package com.vinicius_almeida.mancala.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class GameLocalRepositoryTest {

    @Test
    public void saveNewGameTest() {
        var gameRepository = new GameLocalRepository();
        var game = gameRepository.save(new Game());
        Assertions.assertNotNull(game.getId());
    }

    @Test
    public void saveExistingGameTest() {
        var game = new Game();
        var gameId = UUID.randomUUID();
        game.setId(gameId);
        var gameRepository = new GameLocalRepository();

        game = gameRepository.save(game);
        Assertions.assertNotNull(game.getId());
        Assertions.assertEquals(gameId, game.getId());
    }

    @Test
    public void findExistingGameTest() {
        var gameRepository = new GameLocalRepository();
        var game = gameRepository.save(new Game());

        var foundGame = gameRepository.findById(game.getId());
        Assertions.assertTrue(foundGame.isPresent());
        Assertions.assertEquals(game, foundGame.get());
    }

    @Test
    public void notFindGameTest() {
        var gameRepository = new GameLocalRepository();

        var gameOptional = gameRepository.findById(UUID.randomUUID());
        Assertions.assertTrue(gameOptional.isEmpty());
    }
}
