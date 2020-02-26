package com.vinicius_almeida.mancala.game;

import com.vinicius_almeida.mancala.game.board.BoardStatus;
import com.vinicius_almeida.mancala.game.board.IBoardService;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class GameController implements IGameController {

    private final IBoardService boardService;
    private final IGameRepository gameRepository;

    public GameController(IBoardService boardService, IGameRepository gameRepository) {
        this.boardService = boardService;
        this.gameRepository = gameRepository;
    }


    @Override
    public GameStatus newGame() {
        var game = gameRepository.save(new Game());
        var boardStatus = boardService.getStatus(game.getBoard());
        return new GameStatus(game.getId(), game.getCurrentPlayer(), boardStatus.getPlayersPits());
    }

    @Override
    public GameStatus move(UUID id, Player player, int position) {
        var game = gameRepository.findById(id).orElseThrow();
        var board = game.getBoard();
        var boardStatus = boardService.move(board, player, position);
        var nextTurnPlayer = player.getNextPlayer();

        if (boardStatus.getFinishPitOwner() == player) {
            var currentPlayerPits = boardStatus.getPlayersPits().get(player);
            var finalPosition = boardStatus.getFinalPosition();
            if (finalPosition == currentPlayerPits.size() - 1) {
                nextTurnPlayer = player;
            } else if (currentPlayerPits.get(finalPosition) == 1) {
                var opponentPlayerPits = boardStatus.getPlayersPits().get(player.getNextPlayer());
                var opponentPosition = Math.abs(finalPosition - (opponentPlayerPits.size() - 2));
                if (opponentPlayerPits.get(opponentPosition) != 0) {
                    boardStatus = boardService.takeOpponentStones(board, player, finalPosition, opponentPosition);
                }
            }
        }

        var statusPlayerPits = boardStatus.getPlayersPits();
        if (!allPlayersHasStones(boardStatus)) {
            var winner = checkWinner(boardStatus);
            return new GameStatus(id, nextTurnPlayer, boardStatus.getPlayersPits(), true, winner);
        }

        return new GameStatus(id, nextTurnPlayer, statusPlayerPits);
    }

    private boolean allPlayersHasStones(BoardStatus boardStatus) {
        for (var player : Player.values()) {
            var playerPits = boardStatus.getPlayersPits().get(player);
            boolean playerHasStones = false;
            for (int i = 0; i < playerPits.size() - 1; i++) {
                var pit = playerPits.get(i);
                if (pit > 0) {
                    playerHasStones = true;
                    break;
                }
            }
            if (!playerHasStones) {
                return false;
            }
        }
        return true;
    }

    private Player checkWinner(BoardStatus boardStatus) {
        Player winner = null;
        int winnerTotalStones = 0;
        var playersPits = boardStatus.getPlayersPits();
        for (var player : Player.values()) {
            var playerPits = playersPits.get(player);
            var playerTotalStones = 0;
            for (var pit : playerPits) {
                playerTotalStones += pit;
            }
            if (playerTotalStones > winnerTotalStones) {
                winner = player;
                winnerTotalStones = playerTotalStones;
            } else if (playerTotalStones == winnerTotalStones) {
                winner = null;
            }
        }
        return winner;
    }

}
