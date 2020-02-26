package com.vinicius_almeida.mancala.web;

import com.vinicius_almeida.mancala.game.Game;
import com.vinicius_almeida.mancala.game.GameDb;
import com.vinicius_almeida.mancala.game.GameStatus;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/mancala", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {

    @PostMapping
    public GameStatus newGame() {
        return new GameDb(new Game()).getStatus();
    }

    @PutMapping(value = "/{id}/move")
    public GameStatus move(@PathVariable UUID id, @RequestBody Movement movement) {
        return new GameDb(id).move(movement.getPlayer(), movement.getPosition());
    }

}
