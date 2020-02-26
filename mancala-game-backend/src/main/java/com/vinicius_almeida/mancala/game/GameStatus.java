package com.vinicius_almeida.mancala.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameStatus {

    private final UUID id;
    private final Player nextPlayerTurn;
    private final Map<Player, List<Integer>> playersPits;
    private final boolean gameHasEnded;
    private Player winner;

    public GameStatus(
            @JsonProperty("id") UUID id,
            @JsonProperty("nextPlayerTurn") Player nextPlayerTurn,
            @JsonProperty("playersPits") Map<Player, List<Integer>> playersPits) {
        this.id = id;
        this.nextPlayerTurn = nextPlayerTurn;
        this.playersPits = playersPits;
        this.gameHasEnded = false;
    }

    public GameStatus(
            @JsonProperty("id") UUID id,
            @JsonProperty("nextPlayerTurn") Player nextPlayerTurn,
            @JsonProperty("playersPits") Map<Player, List<Integer>> playersPits,
            @JsonProperty("gameHasEnded") boolean gameHasEnded,
            @JsonProperty("winner") Player winner) {
        this.id = id;
        this.nextPlayerTurn = nextPlayerTurn;
        this.playersPits = playersPits;
        this.gameHasEnded = gameHasEnded;
        this.winner = winner;
    }

    public UUID getId() {
        return id;
    }

    public Player getNextPlayerTurn() {
        return nextPlayerTurn;
    }

    public Map<Player, List<Integer>> getPlayersPits() {
        return playersPits;
    }

    public boolean isGameHasEnded() {
        return gameHasEnded;
    }

    public Player getWinner() {
        return winner;
    }
}
