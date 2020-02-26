package com.vinicius_almeida.mancala.game.board;

import com.vinicius_almeida.mancala.game.Player;

import java.util.List;
import java.util.Map;

public class BoardStatus {

    private final Map<Player, List<Integer>> playersPits;
    private Player currentPlayer;
    private Player finishPitOwner;
    private int finalPosition;

    public BoardStatus(Map<Player, List<Integer>> playersPits) {
        this.playersPits = playersPits;
    }

    public BoardStatus(Player currentPlayer, Player finishPitOwner, int finalPosition,
                       Map<Player, List<Integer>> playersPits) {
        this.currentPlayer = currentPlayer;
        this.finishPitOwner = finishPitOwner;
        this.finalPosition = finalPosition;
        this.playersPits = playersPits;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getFinishPitOwner() {
        return finishPitOwner;
    }

    public int getFinalPosition() {
        return finalPosition;
    }

    public Map<Player, List<Integer>> getPlayersPits() {
        return playersPits;
    }
}
