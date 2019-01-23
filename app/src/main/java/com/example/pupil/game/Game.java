package com.example.pupil.game;

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
    private Game game;
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


    private void buildGameField()
    {
        SquareModel[][] field = game.getField();
        for (int i = 0, lenI = field.length; i < lenI; i++ ) {
            TableRow row = new TableRow(this); // создание строки таблицы
            for (int j = 0, lenJ = field[i].length; j < lenJ; j++)
            {
                Button button = new Button(this);
                buttons[i][j] = button;
                button.setOnClickListener(new Listener(i, j)); // установка слушателя, реагирующего на клик по кнопке
                row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT)); // добавление кнопки в строку таблицы
                button.setWidth(160);
                button.setHeight(160);
            }
            tlXO.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT)); // добавление строки в таблицу
        }
    }


    public boolean makeTurn() {
        if (field[x][y].isFilled()) {
            return false;
        }
        field[x][y].fill(getCurrentActivePlayer());
        filled++;
        switchPlayers();
        return true;
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

    public static class Listener implements View.OnClickListener {
        public int x = 0;
        public int y = 0;

        public Listener(int x, int y)
        {
            this.x = x;
            this.y = y;
        }


        @Override
        public void onClick(View v) {

        }
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

    private void gameOver(PlayerModel player)
    {
        CharSequence text = "Player \"" + activePlayer.getName() + "\" won!";
        game.reset();
        refresh();
    }

    public void onClick(View view)
    {
        Button button = (Button) view;
        Game g = game;
        PlayerModel player = g.getCurrentActivePlayer();
        if (makeTurn(x, y))
        {
            button.setText(player.getName());
        }
        PlayerModel winner = g.checkWinner();
        if (winner != null)
        {
            gameOver(winner);
        }
        if (g.isFieldFilled())
        {  // в случае, если поле заполнено
            gameOver();
        }
    }

    public void gameOver() { }

    public boolean makeTurn(int x, int y)
    {
        return game.makeTurn(x, y);
    }

    public void refresh()
    {
        SquareModel[][] field = game.getField();

        for (int i = 0, len = field.length; i < len; i++)
        {
            for (int j = 0, len2 = field[i].length; j < len2; j++)
            {
                if (field[i][j].getPlayer() == null)
                {
                    buttons[i][j].setText("");
                }
                else
                {
                    buttons[i][j].setText(field[i][j].getPlayer().getName());
                }
            }
        }
    }
}