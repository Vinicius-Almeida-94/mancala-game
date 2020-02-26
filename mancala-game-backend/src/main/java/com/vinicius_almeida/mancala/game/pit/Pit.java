package com.vinicius_almeida.mancala.game.pit;

public class Pit implements IPit {

    private int stones;

    public Pit(int stones) {
        this.stones = stones;
    }

    @Override
    public int add(int stones) {
        this.stones += stones;
        return this.stones;
    }

    @Override
    public int totalStones() {
        return stones;
    }

    public void clear() {
        stones = 0;
    }
}
