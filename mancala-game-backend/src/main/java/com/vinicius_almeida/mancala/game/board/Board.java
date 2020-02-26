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

    public BoardStatus getStatus() {
        return new BoardStatus(getStatusPlayersPits(playersPits));
    }

    public BoardStatus move(Player player, int position) {
        var pits = playersPits.get(player);
        validate(position, pits);
        var startingPit = pits.get(position);
        var stones = startingPit.totalStones();
        startingPit.clear();

        var nextPosition = position;
        var currentPitsOwner = player;
        while (stones > 0) {
            nextPosition++;
            if (nextPosition == pits.size()) {
                currentPitsOwner = currentPitsOwner.getNextPlayer();
                nextPosition = 0;
                pits = playersPits.get(currentPitsOwner);
            }

            var pit = pits.get(nextPosition);
            if (!(pit instanceof BigPit) || player == currentPitsOwner) {
                pit.add(1);
                stones--;
            }
        }

        return new BoardStatus(player, currentPitsOwner, nextPosition, getStatusPlayersPits(playersPits));
    }

    public BoardStatus takeOpponentStones(Player player, int playerPosition, int opponentPosition) {
        var playerPits = playersPits.get(player);
        validate(playerPosition, playerPits);

        var playerPit = playerPits.get(playerPosition);
        if (playerPit.totalStones() > 1) {
            throw new IllegalArgumentException("Only previous empty pit could perform this action.");
        }

        var opponentPit = playersPits.get(player.getNextPlayer()).get(opponentPosition);
        if (opponentPit.totalStones() == 0) {
            throw new IllegalArgumentException("It's not possible to take stones from empty pit.");
        }

        var playerBigPit = playerPits.get(6);

        playerBigPit.add(playerPit.totalStones());
        playerPit.clear();

        playerBigPit.add(opponentPit.totalStones());
        opponentPit.clear();
        return new BoardStatus(player, player, playerPosition, getStatusPlayersPits(playersPits));
    }

    private void validate(int position, List<IPit> pits) {
        if (pits.get(position).totalStones() == 0) {
            throw new IllegalArgumentException("Pit stones should be greater than 0");
        }
    }

    private Map<Player, List<Integer>> getStatusPlayersPits(Map<Player, List<IPit>> statusPlayersPits) {
        Map<Player, List<Integer>> result = new HashMap<>();
        for (var statusPlayerPits : statusPlayersPits.entrySet()) {
            List<Integer> pitsResult = new ArrayList<>();
            var pits = statusPlayerPits.getValue();
            for (var pit : pits) {
                pitsResult.add(pit.totalStones());
            }
            result.put(statusPlayerPits.getKey(), pitsResult);
        }
        return result;
    }

}
