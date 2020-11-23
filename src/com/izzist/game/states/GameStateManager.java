package com.izzist.game.states;

import com.izzist.game.GamePanel;
import com.izzist.game.ultility.KeyHandler;
import com.izzist.game.ultility.Vector2D;

import java.awt.*;
import java.util.ArrayList;

public class GameStateManager {

    private ArrayList<GameState> states;

    public static final int MENU = 0;
    public static final int PLAY = 1;
    public static final int PAUSE = 2;
    public static final int GAMEOVER = 3;

    public GameStateManager() {
        states = new ArrayList<GameState>();
        states.add(new PlayState(this));
    }

    public void add(int state) {
        switch (state){
            case MENU:
                states.add(new MenuState(this));
                break;
            case PLAY:
                states.add(new PlayState(this));
                break;
            case PAUSE:
                states.add(new PauseState(this));
                break;
            case GAMEOVER:
                states.add(new GameoverState(this));
                break;
        }
    }

    public void pop(int state) {
        states.remove(state);
    }

    public void addAndPop(int state){
        states.remove(0);
        add(state);
    }

    public void update() {
        for (int i = 0; i < states.size(); i++) {
            states.get(i).update();
        }
    }

    public void input(KeyHandler key) {
        for (int i = 0; i < states.size(); i++) {
            states.get(i).input(key);
        }
    }

    public void render(Graphics2D g2D) {
        for (int i = 0; i < states.size(); i++) {
            states.get(i).render(g2D);
        }
    }
}
