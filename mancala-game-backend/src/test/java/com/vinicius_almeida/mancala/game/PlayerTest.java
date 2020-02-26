package com.vinicius_almeida.mancala.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void assertNextPlayer() {
        Assertions.assertSame(Player.PLAYER2, Player.PLAYER1.getNextPlayer());
        Assertions.assertSame(Player.PLAYER1, Player.PLAYER2.getNextPlayer());
    }

}
