package com.example.pupil.game.checkers;

import com.example.pupil.game.Game;
import com.example.pupil.game.PlayerModel;
import com.example.pupil.game.SquareModel;

public class WinnerCheckerHorizontal implements WinnerCheckerInterface {
    public Game game;
    public  WinnerCheckerHorizontal(Game game){
        this.game=game;
    }

    @Override
    public PlayerModel checkWinner() {
        SquareModel[][] field = game.getField();
        PlayerModel currPlayer;
        PlayerModel lastPlayer = null;
        int i = 0, len = field.length;
        while (i <= len) {
            lastPlayer = null;
            int successCounter = 1;
            int j = 0, len2 = field[i].length;
            while (j <= len2) {
                currPlayer = field[i][j].getPlayer();
                if (currPlayer == lastPlayer && (currPlayer != null && lastPlayer != null)) {
                    successCounter++;
                    if (successCounter == len2) {
                        return currPlayer;
                    }
                }
                lastPlayer = currPlayer;
                j++;
            }
            i++;
        }
        return null;
    }
}
