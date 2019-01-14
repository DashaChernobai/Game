package com.example.pupil.game;

public class Game {
    private SquareModel[][] field;
    private int squareCount;
    private PlayerModel[] players;
    private boolean started;
    private PlayerModel activePlayer;
    private int filled;

    public Game() {
        field = new SquareModel[3][3];
        squareCount = 0;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = new SquareModel();
                squareCount++;
            }
        }
        started = false;
        players = new PlayerModel[2];
        activePlayer = null;
        filled = 0;
    }

    public void start() {
        started = true;
    }


    private void resetPlayers() {
        players[0] = new PlayerModel("X");
        players[1] = new PlayerModel("O");
        setCurrentActivePlayer(players[0]);
    }


    private void setCurrentActivePlayer(PlayerModel player) {
        activePlayer = player;
    }


    public SquareModel[][] getField(){
        return field;
    }


    public boolean makeTurn(int x, int y) {
        if (field[x][y].isFilled()) {
            return false;
        }
        field[x][y].filPlayer(getCurrentActivePlayer());
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
                anAField.filPlayer(null);
            }
        }
        filled = 0;
    }
}