package com.vinicius_almeida.mancala.game;

import com.vinicius_almeida.mancala.game.board.Board;

import java.util.UUID;

public class Game {

    private UUID id;
    private final Board board;
    private Player currentPlayer;

    public Game() {
        this(new Board(), Player.PLAYER1);
    }

    public Game(Board board, Player player) {
        this.board = board;
        this.currentPlayer = player;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
