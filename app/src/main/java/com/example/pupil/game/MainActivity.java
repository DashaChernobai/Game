package com.example.pupil.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {
    private TableLayout tlXO;
    private Button[][] buttons = new Button[3][3];
    public Game game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tlXO = findViewById(R.id.tlXO);
        game.buildGameField();
        game = new Game();
        game.start();
    }

}
