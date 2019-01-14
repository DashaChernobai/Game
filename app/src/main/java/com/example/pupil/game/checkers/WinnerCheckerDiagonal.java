package com.example.pupil.game.checkers;

import com.example.pupil.game.Game;
import com.example.pupil.game.PlayerModel;
import com.example.pupil.game.SquareModel;

public class WinnerCheckerDiagonal implements WinnerCheckerInterface {
    private  Game game;
    @Override
    public PlayerModel checkWinner() {
        Game game = null;
        SquareModel[][] field = game.getField();
        PlayerModel currPlayer = null;
        PlayerModel lastPlayer;
        for (int i = 0, len = field.length; i < len; i++) {
            lastPlayer = null;
            int successCounter = 1;
            for (int j = 0, len2 = field[i].length; j < len2; j++) {
                currPlayer = field[j][i].getPlayer();
                if (currPlayer == lastPlayer && (currPlayer != null && lastPlayer != null)) {
                    successCounter++;
                    if (successCounter == len2) return currPlayer;
                }
            }
            lastPlayer = currPlayer;

        }
        return null;
    }
}
