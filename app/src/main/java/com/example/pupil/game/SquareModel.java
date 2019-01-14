package com.example.pupil.game;

public class SquareModel {
    private PlayerModel player = null;

    public void filPlayer(PlayerModel player) {
        this.player = player;
    }

    public boolean isFilled() {
        return player != null;
    }

    public PlayerModel getPlayer() {
        return player;
    }

}
