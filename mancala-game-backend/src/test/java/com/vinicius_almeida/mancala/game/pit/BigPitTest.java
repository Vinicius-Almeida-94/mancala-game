package com.vinicius_almeida.mancala.game.pit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BigPitTest {

    @Test
    public void addTest() {
        var bigPit = new BigPit(new Pit(0));
        var totalStones = bigPit.totalStones();
        var addNumber = 1;
        var expected = totalStones + addNumber;
        Assertions.assertEquals(expected, bigPit.add(addNumber));
    }

    @Test
    public void clearThrowsUnsupportedOperationExceptionTest() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> new BigPit(new Pit(0)).clear());
    }

}
