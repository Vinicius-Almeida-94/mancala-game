package com.vinicius_almeida.mancala.game;

import java.util.UUID;

public interface IGame {

    UUID getId();

    GameStatus getStatus();

    GameStatus move(Player player, int position);

}
