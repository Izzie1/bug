package com.izzist.game.managers;

import com.izzist.game.entity.Bomb.Bomb;
import com.izzist.game.entity.Bomb.Flame;
import com.izzist.game.entity.Player;
import com.izzist.game.states.PlayState;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class BombManager {

    public static ArrayList<Bomb> bombs;

    public BombManager() {
        bombs = new ArrayList<>();

    }

    public void update() {
        addBomb();
        removeBomb();
    }

    public void render(Graphics2D g2D) {
        renderBomb(g2D);
    }

    public void addBomb() {
        bombs.addAll(PlayState.player.getBombs());
    }


    public void renderBomb(Graphics2D g2D) {
        for (Bomb bomb : bombs) {
            bomb.render(g2D);
        }
    }

    public void removeBomb() {
        for (int i = bombs.size() - 1; i >= 0; i--) {
            if (bombs.get(i).getIsExploded()) {
                bombs.remove(i);
            }
        }
    }

    public static Bomb getBomb(int x, int y) {
        Bomb temp = null;
        for (Bomb b : bombs) {
            if ((int)b.getPosition().x/32 == x && (int)b.getPosition().y/32 == y) {
                temp = b;
            }
        }
        return temp;
    }
}
