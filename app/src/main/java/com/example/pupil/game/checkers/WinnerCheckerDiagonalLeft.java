package com.example.pupil.game.checkers;

import com.example.pupil.game.Game;
import com.example.pupil.game.PlayerModel;
import com.example.pupil.game.SquareModel;

public class WinnerCheckerDiagonalLeft implements WinnerCheckerInterface {
    private Game game;

    public WinnerCheckerDiagonalLeft(Game game) {
        this.game = game;
    }

    @Override
    public PlayerModel checkWinner() {
        Game game = null;
        SquareModel[][] field = game.getField();
        PlayerModel currPlayer = null;
        PlayerModel lastPlayer = null;
        int successCounter = 1;
        for (int i = 1, len = field.length; i < len; i++) {
            currPlayer = field[i][i].getPlayer();
            if (currPlayer != null) {
                if (successCounter == field.length) {
                    return currPlayer;
                }
            }
            lastPlayer = currPlayer;
        }

        return null;

    }
}
