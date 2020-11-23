package com.izzist.game.states;

import com.izzist.game.entity.Bomb.Flame;
import com.izzist.game.entity.Enemy.Balloom;
import com.izzist.game.entity.Player;
import com.izzist.game.managers.BombManager;
import com.izzist.game.managers.MapManager;
import com.izzist.game.ultility.KeyHandler;
import com.izzist.game.ultility.Vector2D;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayState extends GameState {
    private MapManager mapManager;
    public static Player player;
    private BombManager bombManager;
    public static List<Flame> flames = new ArrayList<>();
    private Balloom balloom = new Balloom(new Vector2D(64,32),32);


    public PlayState(GameStateManager gameStateManager) {
        super((gameStateManager));
        mapManager = new MapManager();
        bombManager = new BombManager();
    }

    @Override
    public void update() {
        player.update();
        bombManager.update();
        updateFlame();
        removeFlame();
        mapManager.getMap(0).update();
        balloom.update();
    }

    @Override
    public void input(KeyHandler key) {
        player.input(key);
    }

    @Override
    public void render(Graphics2D g2D) {
        mapManager.getMap(0).render(g2D);
        bombManager.renderBomb(g2D);
        renderFlame(g2D);
        balloom.render(g2D);
        player.render(g2D);

    }

    public void renderFlame(Graphics2D g2D){
        for (Flame flame : flames) {
            flame.render(g2D);
        }
    }

    public void updateFlame(){
        for (Flame flame : flames) {
            flame.update();
        }
    }

    public void removeFlame() {
        for (int i = flames.size() - 1; i >= 0; i--) {
            if (flames.get(i).getIsExploded()) {
                flames.remove(i);
            }
        }
    }

}
