package com.vinicius_almeida.mancala.web;

import com.vinicius_almeida.mancala.game.GameStatus;
import com.vinicius_almeida.mancala.game.IGameController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/mancala", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameRestController {

    private final IGameController gameController;

    public GameRestController(IGameController gameController) {
        this.gameController = gameController;
    }

    @PostMapping
    public GameStatus newGame() {
        return gameController.newGame();
    }

    @PutMapping(value = "/{id}/move")
    public GameStatus move(@PathVariable UUID id, @RequestBody Movement movement) {
        return gameController.move(id, movement.getPlayer(), movement.getPosition());
    }

}
