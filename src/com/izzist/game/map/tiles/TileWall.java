package com.izzist.game.map.tiles;

import com.izzist.game.entity.Entity;
import com.izzist.game.graphics.Sprite;
import com.izzist.game.ultility.AABB;
import com.izzist.game.ultility.Vector2D;

import java.awt.*;

public class TileWall extends Tile{


    public TileWall(Vector2D position,int size) {
        super(position,size);
        this.sprite = new Sprite("tile/Wall.png", 16, 16);
    }

    @Override
    public void render(Graphics2D g2D) {
        g2D.setColor(Color.RED);
        g2D.drawRect((int) (position.x), (int) (position.y ), 32, 32);
        g2D.drawImage(sprite.getSprite(1,1), (int) (position.x), (int) (position.y), size, size, null);
    }

    @Override
    public boolean isCollide(Entity entity) {
        return true;
    }
}
