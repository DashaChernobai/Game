package com.example.pupil.game;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.pupil.game.checkers.WinnerCheckerDiagonalLeft;
import com.example.pupil.game.checkers.WinnerCheckerDiagonalRight;
import com.example.pupil.game.checkers.WinnerCheckerInterface;
import com.example.pupil.game.checkers.WinnerCheckerVertical;
import com.example.pupil.game.checkers.WinnerCheckerHorizontal;

import static android.widget.Toast.LENGTH_SHORT;

public class Game {
    public static SquareModel[][] field;
    public int squareCount;
    public PlayerModel[] players;
    public boolean started;
    public PlayerModel activePlayer;
    public int filled;
    public WinnerCheckerInterface[] winnerCheckers;
    public Button[][] buttons = new Button[3][3];
    public TableLayout tlXO;
    public Game game;
    public int x;
    public int y;

    public Game() {
        field = new SquareModel[3][3];
        squareCount = 0;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = new SquareModel();
                squareCount++;
            }
        }

        winnerCheckers = new WinnerCheckerInterface[4];
        winnerCheckers[0] = new WinnerCheckerHorizontal(this);
        winnerCheckers[1] = new WinnerCheckerVertical(this);
        winnerCheckers[2] = new WinnerCheckerDiagonalLeft(this);
        winnerCheckers[3] = new WinnerCheckerDiagonalRight( this);

    }

    public static SquareModel[][] getField(){
        return field;
    }



    public void start() {
        started = true;
    }

    public void resetPlayers() {
        players[0] = new PlayerModel("X");
        players[1] = new PlayerModel("O");
        setCurrentActivePlayer(players[0]);
    }


    public void setCurrentActivePlayer(PlayerModel player) {
        activePlayer = player;
    }


    




    private void switchPlayers() {
        activePlayer = (activePlayer == players[0] ? players[1] : players[0]);
    }


    public PlayerModel getCurrentActivePlayer() {
        return activePlayer;
    }


    public boolean isFieldFilled() {
        return squareCount == filled;
    }


    public  void reset(){
        resetField();
        resetPlayers();
    }


    private void resetField() {
        for (SquareModel[] aField : field) {
            for (SquareModel anAField : aField) {
                anAField.fillPlayer(null);
            }
        }
        filled = 0;
    }




    public boolean makeTurn(int x, int y) {
        if (field[x][y].isFilled()) {
            return false;
        }
        field[x][y].fill(getCurrentActivePlayer());
        filled++;
        switchPlayers();
        return true;
    }
    public PlayerModel checkWinner()
    {
        for (WinnerCheckerInterface winChecker : winnerCheckers)
        {
            PlayerModel winner = winChecker.checkWinner();
            if (winner != null)
            {
                return winner;
            }
        }
        return null;
    }


    public static class Listener implements View.OnClickListener
    {
        private int x = 0;
        private int y = 0;

        public Listener(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public void onClick(View view)
        {
            Button button = (Button) view;
        }
    }


    }