package com.vinicius_almeida.mancala.game;

import com.vinicius_almeida.mancala.game.board.Board;
import com.vinicius_almeida.mancala.game.board.BoardStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameTest {

    @Test
    public void opponentNextTurnTest() {
        Map<Player, List<Integer>> statusPlayersPits = new HashMap<>();
        for (var player : Player.values()) {
            statusPlayersPits.put(player, Arrays.asList(1, 1));
        }

        var boardStatus = new BoardStatus(Player.PLAYER1, Player.PLAYER2, 5, statusPlayersPits);
        var board = Mockito.mock(Board.class);
        Mockito.when(board.move(Player.PLAYER1, 0)).thenReturn(boardStatus);

        var gameStatus = new Game(board, Player.PLAYER1).move(Player.PLAYER1, 0);
        Assertions.assertSame(Player.PLAYER2, gameStatus.getNextPlayerTurn());
        Assertions.assertFalse(gameStatus.isGameHasEnded());
    }

    @Test
    public void currentPlayerTakesOpponentStonesTest() {
        Map<Player, List<Integer>> statusPlayersPits = new HashMap<>();
        for (var player : Player.values()) {
            statusPlayersPits.put(player, Arrays.asList(1, 1));
        }

        var boardStatus = new BoardStatus(Player.PLAYER1, Player.PLAYER1, 0, statusPlayersPits);
        var board = Mockito.mock(Board.class);
        Mockito.when(board.move(Player.PLAYER1, 0)).thenReturn(boardStatus);
        Mockito.when(board.takeOpponentStones(Player.PLAYER1, 0, 0)).thenReturn(boardStatus);

        var gameStatus = new Game(board, Player.PLAYER1).move(Player.PLAYER1, 0);
        Assertions.assertSame(Player.PLAYER2, gameStatus.getNextPlayerTurn());
        Assertions.assertFalse(gameStatus.isGameHasEnded());
    }

    @Test
    public void samePlayerNextTurnTest() {
        Map<Player, List<Integer>> statusPlayersPits = new HashMap<>();
        for (var player : Player.values()) {
            statusPlayersPits.put(player, Arrays.asList(1, 1));
        }

        var boardStatus = new BoardStatus(Player.PLAYER1, Player.PLAYER1, 1, statusPlayersPits);
        var board = Mockito.mock(Board.class);
        Mockito.when(board.move(Player.PLAYER1, 0)).thenReturn(boardStatus);

        var gameStatus = new Game(board, Player.PLAYER1).move(Player.PLAYER1, 0);
        Assertions.assertSame(Player.PLAYER1, gameStatus.getNextPlayerTurn());
        Assertions.assertFalse(gameStatus.isGameHasEnded());
    }

    @Test
    public void endGameCurrentPlayerWins() {
        Map<Player, List<Integer>> statusPlayersPits = new HashMap<>();
        for (var player : Player.values()) {
            statusPlayersPits.put(player, Arrays.asList(0, 0));
        }
        statusPlayersPits.put(Player.PLAYER1, Arrays.asList(0, 1));

        var boardStatus = new BoardStatus(Player.PLAYER1, Player.PLAYER1, 0, statusPlayersPits);
        var board = Mockito.mock(Board.class);
        Mockito.when(board.move(Player.PLAYER1, 0)).thenReturn(boardStatus);

        var gameStatus = new Game(board, Player.PLAYER1).move(Player.PLAYER1, 0);
        Assertions.assertTrue(gameStatus.isGameHasEnded());
        Assertions.assertSame(Player.PLAYER1, gameStatus.getWinner());
    }

    @Test
    public void endGameOpponentWins() {
        Map<Player, List<Integer>> statusPlayersPits = new HashMap<>();
        for (var player : Player.values()) {
            statusPlayersPits.put(player, Arrays.asList(0, 0));
        }
        statusPlayersPits.put(Player.PLAYER2, Arrays.asList(0, 1));

        var boardStatus = new BoardStatus(Player.PLAYER1, Player.PLAYER1, 0, statusPlayersPits);
        var board = Mockito.mock(Board.class);
        Mockito.when(board.move(Player.PLAYER1, 0)).thenReturn(boardStatus);

        var gameStatus = new Game(board, Player.PLAYER1).move(Player.PLAYER1, 0);
        Assertions.assertTrue(gameStatus.isGameHasEnded());
        Assertions.assertSame(Player.PLAYER2, gameStatus.getWinner());
    }

    @Test
    public void endGameTie() {
        Map<Player, List<Integer>> statusPlayersPits = new HashMap<>();
        for (var player : Player.values()) {
            statusPlayersPits.put(player, Arrays.asList(0, 1));
        }

        var boardStatus = new BoardStatus(Player.PLAYER1, Player.PLAYER1, 0, statusPlayersPits);
        var board = Mockito.mock(Board.class);
        Mockito.when(board.move(Player.PLAYER1, 0)).thenReturn(boardStatus);

        var gameStatus = new Game(board, Player.PLAYER1).move(Player.PLAYER1, 0);
        Assertions.assertTrue(gameStatus.isGameHasEnded());
        Assertions.assertNull(gameStatus.getWinner());
    }

}
