package com.izzist.game.entity.Bomb;

import com.izzist.game.entity.Entity;
import com.izzist.game.entity.Player;
import com.izzist.game.graphics.Animation;
import com.izzist.game.graphics.Sprite;
import com.izzist.game.states.PlayState;
import com.izzist.game.ultility.Vector2D;

import java.awt.*;
import java.awt.datatransfer.FlavorEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class Bomb extends Entity {
    private int explodeTime = 160;
    private boolean isExploded = false;
    private Rectangle rectangle;


    public Bomb(Vector2D position, int size) {
        this.position = position;
        this.size = size;
        this.sprite = new Sprite("font/BombGreen_16_16.png", 16, 16);
        animation = new Animation();
        setAnimation2(sprite.getSpriteArray2(0), 25);
        int xt = (int) (position.x + 16) / 32;
        int yt = (int) (position.y + 16) / 32;
        this.position.x = xt * 32;
        this.position.y = yt * 32;
        rectangle = new Rectangle((int) this.position.x,
                (int) this.position.y, size, size);
    }

    @Override
    public void render(Graphics2D g2D) {
        g2D.drawImage(animation.getImage(), (int) (position.x), (int) (position.y), size, size, null);
    }

    @Override
    public void update() {
        animation.update();
        if (explodeTime > 0) {
            isExploded = false;
            explodeTime--;
            if(flameCollision()){
                isExploded = true;
                explodeTime = 160;
            }
        } else if (explodeTime == 0  ) {
            isExploded = true;
            explodeTime = 160;
        }
    }

    public boolean flameCollision() {
        if (PlayState.flames != null) {
            for (Flame f : PlayState.flames) {
                if (f.getRectangles() != null) {
                    for (Rectangle rect : f.getRectangles()) {
                        if (rectangle.intersects(rect)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    public int getExplodeTime() {
        return explodeTime;
    }

    public void setExplodeTime(int explodeTime) {
        this.explodeTime = explodeTime;
    }

    public void setIsExploded(boolean exploded) {
        this.isExploded = exploded;
    }

    public boolean getIsExploded() {
        return isExploded;
    }
}
