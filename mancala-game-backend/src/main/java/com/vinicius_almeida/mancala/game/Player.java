package com.vinicius_almeida.mancala.game;

public enum Player {

    PLAYER1,
    PLAYER2;

    static {
        PLAYER1.nextPlayer = PLAYER2;
        PLAYER2.nextPlayer = PLAYER1;
    }

    private Player nextPlayer;

    public Player getNextPlayer() {
        return nextPlayer;
    }
}
