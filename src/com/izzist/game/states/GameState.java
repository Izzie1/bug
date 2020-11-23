package com.izzist.game.states;

import com.izzist.game.ultility.KeyHandler;

import java.awt.Graphics2D;

public abstract class GameState {
    protected GameStateManager gameStateManager;

    public GameState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    public abstract void update();

    public abstract void input(KeyHandler key);

    public abstract void render(Graphics2D g2D);

}
