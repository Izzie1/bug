package com.izzist.game.entity;

import com.izzist.game.graphics.Animation;
import com.izzist.game.graphics.Sprite;
import com.izzist.game.ultility.AABB;
import com.izzist.game.ultility.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected int currentAnimation;
    protected Animation animation;
    protected Sprite sprite;
    protected Vector2D position;
    protected int size;
    protected AABB bounds;


    public void setAnimation(int i, BufferedImage[] frames, int delay) {
        currentAnimation = i;
        animation.setFrames(frames);
        animation.setDelay(delay);
    }
    public void setAnimation2( BufferedImage[] frames, int delay) {
        animation.setFrames(frames);
        animation.setDelay(delay);
    }

    public abstract void render(Graphics2D g2D);

    public abstract void update();

    public int getSize() {
        return size;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }


}
