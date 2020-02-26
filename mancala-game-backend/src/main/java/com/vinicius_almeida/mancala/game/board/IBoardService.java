package com.vinicius_almeida.mancala.game.board;

import com.vinicius_almeida.mancala.game.Player;

public interface IBoardService {

    BoardStatus getStatus(Board board);

    BoardStatus move(Board board, Player player, int position);

    BoardStatus takeOpponentStones(Board board, Player player, int playerPosition, int opponentPosition);
}
