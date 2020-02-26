package com.vinicius_almeida.mancala.game;

import com.vinicius_almeida.mancala.game.board.Board;
import com.vinicius_almeida.mancala.game.board.BoardStatus;

import java.util.UUID;

public class Game implements IGame {

    private final UUID id;
    private final Board board;
    private Player currentPlayer;

    public Game() {
        this(UUID.randomUUID(), new Board(), Player.PLAYER1);
    }

    public Game(Board board, Player player) {
        this(UUID.randomUUID(), board, player);
    }

    public Game(UUID id, Board board, Player player) {
        this.id = id;
        this.board = board;
        this.currentPlayer = player;
    }

    public UUID getId() {
        return id;
    }

    public GameStatus getStatus() {
        return new GameStatus(id, currentPlayer, board.getStatus().getPlayersPits());
    }

    public GameStatus move(Player player, int position) {
        var boardStatus = board.move(player, position);
        var nextTurnPlayer = player.getNextPlayer();

        if (boardStatus.getFinishPitOwner() == player) {
            var currentPlayerPits = boardStatus.getPlayersPits().get(player);
            var finalPosition = boardStatus.getFinalPosition();
            if (finalPosition == currentPlayerPits.size() - 1) {
                nextTurnPlayer = player;
            } else if (currentPlayerPits.get(finalPosition) == 1) {
                var opponentPlayerPits = boardStatus.getPlayersPits().get(player.getNextPlayer());
                var opponentPosition = Math.abs(finalPosition - (opponentPlayerPits.size() - 2));
                if (opponentPlayerPits.get(opponentPosition) != 0) {
                    boardStatus = board.takeOpponentStones(player, finalPosition, opponentPosition);
                }
            }
        }

        var statusPlayerPits = boardStatus.getPlayersPits();
        if (!allPlayersHasStones(boardStatus)) {
            var winner = checkWinner(boardStatus);
            return new GameStatus(id, nextTurnPlayer, boardStatus.getPlayersPits(), true, winner);
        }

        return new GameStatus(id, nextTurnPlayer, statusPlayerPits);
    }

    private boolean allPlayersHasStones(BoardStatus boardStatus) {
        for (var player : Player.values()) {
            var playerPits = boardStatus.getPlayersPits().get(player);
            boolean playerHasStones = false;
            for (int i = 0; i < playerPits.size() - 1; i++) {
                var pit = playerPits.get(i);
                if (pit > 0) {
                    playerHasStones = true;
                    break;
                }
            }
            if (!playerHasStones) {
                return false;
            }
        }
        return true;
    }

    private Player checkWinner(BoardStatus boardStatus) {
        Player winner = null;
        int winnerTotalStones = 0;
        var playersPits = boardStatus.getPlayersPits();
        for (var player : Player.values()) {
            var playerPits = playersPits.get(player);
            var playerTotalStones = 0;
            for (var pit : playerPits) {
                playerTotalStones += pit;
            }
            if (playerTotalStones > winnerTotalStones) {
                winner = player;
                winnerTotalStones = playerTotalStones;
            } else if (playerTotalStones == winnerTotalStones) {
                winner = null;
            }
        }
        return winner;
    }

}
