package com.vinicius_almeida.mancala.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinicius_almeida.mancala.game.Player;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movement {

    private final Player player;
    private final Integer position;

    public Movement(@JsonProperty("player") Player player,
                    @JsonProperty("position") Integer position) {
        this.player = player;
        this.position = position;
    }

    public Player getPlayer() {
        return player;
    }

    public Integer getPosition() {
        return position;
    }

}
