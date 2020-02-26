package com.vinicius_almeida.mancala.game.board;

import com.vinicius_almeida.mancala.game.Player;
import com.vinicius_almeida.mancala.game.pit.BigPit;
import com.vinicius_almeida.mancala.game.pit.IPit;
import com.vinicius_almeida.mancala.game.pit.Pit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardControllerTest {

    @Test
    public void invalidPositionMove() {
        var board = new Board(0);

        var boardService = new BoardService();
        Assertions.assertThrows(IllegalArgumentException.class, () -> boardService.move(board, Player.PLAYER1, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> boardService.move(board, Player.PLAYER1, 6));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> boardService.move(board, Player.PLAYER1, -1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> boardService.move(board, Player.PLAYER1, 7));
    }

    @Test
    public void samePlayerMove() {
        var board = new Board(6);

        var boardService = new BoardService();
        var status = boardService.move(board, Player.PLAYER1, 0);
        Assertions.assertSame(Player.PLAYER1, status.getCurrentPlayer());
        Assertions.assertSame(Player.PLAYER1, status.getFinishPitOwner());
        Assertions.assertEquals(6, status.getFinalPosition());
        var playersPits = status.getPlayersPits();

        var player1Pits = playersPits.get(Player.PLAYER1);
        Assertions.assertEquals(0, player1Pits.get(0));
        for (int i = 1; i < 6; i++) {
            Assertions.assertEquals(7, player1Pits.get(i));
        }
        Assertions.assertEquals(1, player1Pits.get(6));

        var player2Pits = playersPits.get(Player.PLAYER2);
        for (int i = 0; i < 6; i++) {
            Assertions.assertEquals(6, player2Pits.get(i));
        }
        Assertions.assertEquals(0, player2Pits.get(6));
    }

    @Test
    public void finishAtOtherPlayerMove() {
        var board = new Board(6);

        var boardService = new BoardService();
        var status = boardService.move(board, Player.PLAYER1, 1);
        Assertions.assertSame(Player.PLAYER1, status.getCurrentPlayer());
        Assertions.assertSame(Player.PLAYER2, status.getFinishPitOwner());
        Assertions.assertEquals(0, status.getFinalPosition());
        var playersPits = status.getPlayersPits();

        var player1Pits = playersPits.get(Player.PLAYER1);
        Assertions.assertEquals(6, player1Pits.get(0));
        Assertions.assertEquals(0, player1Pits.get(1));
        for (int i = 2; i < 6; i++) {
            Assertions.assertEquals(7, player1Pits.get(i));
        }
        Assertions.assertEquals(1, player1Pits.get(6));

        var player2Pits = playersPits.get(Player.PLAYER2);
        Assertions.assertEquals(7, player2Pits.get(0));
        for (int i = 1; i < 6; i++) {
            Assertions.assertEquals(6, player2Pits.get(i));
        }
        Assertions.assertEquals(0, player2Pits.get(6));
    }

    @Test
    public void notAddOpponentBigPitTest() {
        var board = new Board(8);

        var boardService = new BoardService();
        var status = boardService.move(board, Player.PLAYER1, 5);
        Assertions.assertSame(Player.PLAYER1, status.getCurrentPlayer());
        Assertions.assertSame(Player.PLAYER1, status.getFinishPitOwner());
        Assertions.assertEquals(0, status.getFinalPosition());
        var playersPits = status.getPlayersPits();

        var player1Pits = playersPits.get(Player.PLAYER1);
        Assertions.assertEquals(9, player1Pits.get(0));
        for (int i = 1; i < 5; i++) {
            Assertions.assertEquals(8, player1Pits.get(i));
        }
        Assertions.assertEquals(0, player1Pits.get(5));
        Assertions.assertEquals(1, player1Pits.get(6));

        var player2Pits = playersPits.get(Player.PLAYER2);
        for (int i = 0; i < 6; i++) {
            Assertions.assertEquals(9, player2Pits.get(i));
        }
        Assertions.assertEquals(0, player2Pits.get(6));
    }


    @Test
    public void invalidTakeOpponentPosition() {
        var emptyBoard = new Board(0);

        var boardService = new BoardService();
        Assertions.assertThrows(IllegalArgumentException.class, () -> boardService.takeOpponentStones(emptyBoard, Player.PLAYER1, 0, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> boardService.takeOpponentStones(emptyBoard, Player.PLAYER1, 6, 6));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> boardService.takeOpponentStones(emptyBoard, Player.PLAYER1, -1, -1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> boardService.takeOpponentStones(emptyBoard, Player.PLAYER1, 7, 7));
        Assertions.assertThrows(IllegalArgumentException.class, () -> boardService.takeOpponentStones(new Board(2), Player.PLAYER1, 0, 0));

        Map<Player, List<IPit>> pits = new HashMap<>();
        pits.put(Player.PLAYER1, Arrays.asList(new Pit(1), new BigPit(new Pit(0))));
        pits.put(Player.PLAYER2, Arrays.asList(new Pit(0), new BigPit(new Pit(0))));
        var board = new Board(pits);
        Assertions.assertThrows(IllegalArgumentException.class, () -> boardService.takeOpponentStones(board, Player.PLAYER1, 0, 0));
    }

    @Test
    public void takeOpponentStonesTest() {
        var board = new Board(1);

        var boardService = new BoardService();
        var playersPits = boardService.takeOpponentStones(board, Player.PLAYER1, 0, 5).getPlayersPits();

        var player1Pits = playersPits.get(Player.PLAYER1);
        Assertions.assertEquals(0, player1Pits.get(0));
        Assertions.assertEquals(2, player1Pits.get(6));

        var player2Pits = playersPits.get(Player.PLAYER2);
        Assertions.assertEquals(0, player2Pits.get(5));
        Assertions.assertEquals(0, player2Pits.get(6));
    }
}
