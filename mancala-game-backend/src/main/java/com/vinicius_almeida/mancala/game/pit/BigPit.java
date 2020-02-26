package com.vinicius_almeida.mancala.game.pit;

public class BigPit implements IPit {

    private final IPit origin;

    public BigPit(IPit origin) {
        this.origin = origin;
    }

    @Override
    public int add(int stones) {
        return origin.add(stones);
    }

    @Override
    public int totalStones() {
        return origin.totalStones();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Big pit cannot be cleared.");
    }
}
