package com.example.pupil.game;

public class PlayerModel {
    private String name;

    public PlayerModel(String name) {
        this.name = name;
    }

    private String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
