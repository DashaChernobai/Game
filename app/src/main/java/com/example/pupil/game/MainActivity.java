package com.example.pupil.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import static com.example.pupil.game.Game.field;


public class MainActivity extends AppCompatActivity {

    public TableLayout tlXO;
    public Button[][] buttons = new Button[3][3];
    public Game game;
    public PlayerModel activePlayer;
int x,y;

    public void gameOver(PlayerModel player) {
        CharSequence text = "Player \"" + activePlayer.getName() + "\" won!";
        game.reset();
        refresh();
    }


    public void refresh() {
        SquareModel[][] field = game.getField();

        for (int i = 0, len = field.length; i < len; i++) {
            for (int j = 0, len2 = field[i].length; j < len2; j++) {
                if (field[i][j].getPlayer() == null) {
                    buttons[i][j].setText("");
                } else {
                    buttons[i][j].setText(field[i][j].getPlayer().getName());
                }
            }
        }
    }





    public void onClick(View view) {
        Button button = (Button) view;
        Game g = game;
        PlayerModel player = g.getCurrentActivePlayer();
        if (g.makeTurn(x, y)) {
            button.setText(player.getName());
        }
        PlayerModel winner = g.checkWinner();
        if (winner != null) {
            gameOver(winner);
        }
        if (g.isFieldFilled()) {  // в случае, если поле заполнено
            gameOver();
        }
    }

    public void gameOver() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tlXO = findViewById(R.id.tlXO);

        buildGameField();
        game = new Game();
        game.start();

    }


    public void buildGameField() {
        SquareModel[][] field = game.getField();
        for (int i = 0, lenI = field.length; i < lenI; i++) {
            TableRow row = new TableRow(this); // создание строки таблицы
            for (int j = 0, lenJ = field[i].length; j < lenJ; j++) {
                Button button = new Button(this);
                buttons[i][j] = button;
                button.setOnClickListener(new Game.Listener(i, j)); // установка слушателя, реагирующего на клик по кнопке
                row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT)); // добавление кнопки в строку таблицы
                button.setWidth(160);
                button.setHeight(160);
            }
            tlXO.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT)); // добавление строки в таблицу
        }
    }
}