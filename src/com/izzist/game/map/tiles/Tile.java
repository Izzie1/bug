package com.izzist.game.map.tiles;

import com.izzist.game.entity.Entity;
import com.izzist.game.entity.Player;
import com.izzist.game.graphics.Sprite;
import com.izzist.game.ultility.AABB;
import com.izzist.game.ultility.Vector2D;

import java.awt.*;

public abstract class Tile {
    protected Vector2D position;
    protected Sprite sprite;
    protected int size;

    public Tile(Vector2D position, int size) {
        this.position = position;
        this.size = size;

    }

    public abstract void render(Graphics2D g2D);

    public abstract boolean isCollide(Entity entity);

    public Vector2D getPosition() {
        return position;
    }



}
