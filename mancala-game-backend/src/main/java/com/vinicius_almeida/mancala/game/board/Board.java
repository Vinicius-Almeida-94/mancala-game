package com.vinicius_almeida.mancala.game.board;

import com.vinicius_almeida.mancala.game.Player;
import com.vinicius_almeida.mancala.game.pit.BigPit;
import com.vinicius_almeida.mancala.game.pit.IPit;
import com.vinicius_almeida.mancala.game.pit.Pit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private Map<Player, List<IPit>> playersPits;

    public Board() {
        this(6);
    }

    public Board(int stones) {
        this(new HashMap<>());

        this.playersPits = new HashMap<>();
        for (var player : Player.values()) {
            List<IPit> pits = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                pits.add(new Pit(stones));
            }
            pits.add(new BigPit(new Pit(0)));
            playersPits.put(player, pits);
        }
    }

    public Board(Map<Player, List<IPit>> playersPits) {
        this.playersPits = playersPits;
    }

    public Map<Player, List<IPit>> getPlayersPits() {
        return playersPits;
    }

    public void setPlayersPits(Map<Player, List<IPit>> playersPits) {
        this.playersPits = playersPits;
    }
}
