package com.vinicius_almeida.mancala.game;

import java.util.UUID;

public interface IGameController {

    GameStatus newGame();

    GameStatus move(UUID id, Player player, int position);

}
