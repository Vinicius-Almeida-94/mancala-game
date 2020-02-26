package com.vinicius_almeida.mancala.game;

import java.util.Optional;
import java.util.UUID;

public interface IGameRepository {

    Game save(Game game);

    Optional<Game> findById(UUID id);

}
