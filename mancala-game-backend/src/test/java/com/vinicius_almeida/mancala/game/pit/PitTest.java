package com.vinicius_almeida.mancala.game.pit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PitTest {

    @Test
    public void addTest() {
        var pit = new Pit(6);
        var totalStones = pit.totalStones();
        var addNumber = 1;
        var expected = totalStones + addNumber;
        Assertions.assertEquals(expected, pit.add(addNumber));
    }

    @Test
    public void clearTest() {
        var pit = new Pit(6);
        Assertions.assertTrue(pit.totalStones() > 0);
        pit.clear();
        Assertions.assertEquals(0, pit.totalStones());
    }
}
