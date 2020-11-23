package com.izzist.game.entity.Bomb;

import com.izzist.game.entity.Entity;
import com.izzist.game.graphics.Animation;
import com.izzist.game.graphics.Sprite;
import com.izzist.game.managers.TileManager;
import com.izzist.game.map.tiles.TileWall;
import com.izzist.game.ultility.Vector2D;

import java.awt.*;
import java.util.ArrayList;

public class Flame extends Entity {

    private int explodeTime = 40;
    private int radius = 1;
    private boolean isExploded = false;


    private Animation animation_up;
    private Animation animation_down;
    private Animation animation_left;
    private Animation animation_right;
    private Animation animation_horizontal;//ngang
    private Animation animation_vertical;//doc
    private ArrayList<Rectangle> rectangles;

    public Flame(Vector2D position, int size, int radius) {
        rectangles = new ArrayList<>();
        this.radius = radius;
        this.position = position;
        this.size = size;
        this.sprite = new Sprite("font/Flame_Green_16_16.png", 16, 16);
        animation = new Animation();
        animation.setFrames(sprite.getSpriteArray2(0));
        animation.setDelay(15);
        animation_up = new Animation();
        animation_up.setFrames(sprite.getSpriteArray2(1));
        animation_up.setDelay(15);
        animation_down = new Animation();
        animation_down.setFrames(sprite.getSpriteArray2(4));
        animation_down.setDelay(15);
        animation_right = new Animation();
        animation_right.setFrames(sprite.getSpriteArray2(3));
        animation_right.setDelay(15);
        animation_left = new Animation();
        animation_left.setFrames(sprite.getSpriteArray2(2));
        animation_left.setDelay(15);
        animation_horizontal = new Animation();
        animation_horizontal.setFrames(sprite.getSpriteArray2(6));
        animation_horizontal.setDelay(15);
        animation_vertical = new Animation();
        animation_vertical.setFrames(sprite.getSpriteArray2(5));
        animation_vertical.setDelay(15);
    }

    public void setIsExploded(boolean exploded) {
        this.isExploded = exploded;
    }

    public boolean getIsExploded() {
        return isExploded;
    }

    @Override
    public void render(Graphics2D g2D) {
        g2D.drawImage(animation.getImage(), (int) (position.x), (int) (position.y), size, size, null);
        Rectangle center = new Rectangle((int) (position.x),(int) (position.y),32,32);
        g2D.fillRect(center.x,center.y,center.width,center.height);
        rectangles.add(center);
        //Trai
        for (int i = 1; i <= radius; i++) {
            int x = (int) ((position.x / 32) - i) * 32;
            int y = (int) ((position.y));
            Rectangle temp = new Rectangle(x,y,32,32);
            g2D.drawRect(temp.x,temp.y,temp.width,temp.height);
            rectangles.add(temp);
            if (isWallCollision(x, y)) {
                break;
            } else if (isBrickCollision(x, y)) {
                TileManager.getBrick(x / 32, y / 32).setBreak(true);
                break;
            }
            if (i == radius) {
                g2D.drawImage(animation_left.getImage(), x, y, size, size, null);
            } else {
                g2D.drawImage(animation_horizontal.getImage(), x, y, size, size, null);
            }
        }
        //phai
        for (int i = 1; i <= radius; i++) {
            int x = (int) ((position.x / 32) + i) * 32;
            int y = (int) ((position.y));
            Rectangle temp = new Rectangle(x,y,32,32);
            g2D.drawRect(temp.x,temp.y,temp.width,temp.height);
            rectangles.add(temp);
            if (isWallCollision(x, y)) {
                break;
            } else if (isBrickCollision(x, y)) {
                TileManager.getBrick(x / 32, y / 32).setBreak(true);
                break;
            }
            if (i == radius) {
                g2D.drawImage(animation_right.getImage(), x, y, size, size, null);
            } else {
                g2D.drawImage(animation_horizontal.getImage(), x, y, size, size, null);
            }
        }
        //tren
        for (int i = 1; i <= radius; i++) {
            int x = (int) (position.x);
            int y = (int) ((position.y / 32) - i) * 32;
            Rectangle temp = new Rectangle(x,y,32,32);
            g2D.drawRect(temp.x,temp.y,temp.width,temp.height);
            rectangles.add(temp);
            if (isWallCollision(x, y)) {
                break;
            } else if (isBrickCollision(x, y)) {
                TileManager.getBrick(x / 32, y / 32).setBreak(true);
                break;
            }
            if (i == radius) {
                g2D.drawImage(animation_up.getImage(), x, y, size, size, null);
            } else {
                g2D.drawImage(animation_vertical.getImage(), x, y, size, size, null);
            }
        }
        //duoi
        for (int i = 1; i <= radius; i++) {
            int x = (int) (position.x);
            int y = (int) ((position.y / 32) + i) * 32;
            Rectangle temp = new Rectangle(x,y,32,32);
            g2D.drawRect(temp.x,temp.y,temp.width,temp.height);
            rectangles.add(temp);
            if (isWallCollision(x, y)) {
                break;
            } else if (isBrickCollision(x, y)) {
                TileManager.getBrick(x / 32, y / 32).setBreak(true);
                break;
            }
            if (i == radius) {
                g2D.drawImage(animation_down.getImage(), x, y, size, size, null);
            } else {
                g2D.drawImage(animation_vertical.getImage(), x, y, size, size, null);
            }
        }
    }

    @Override
    public void update() {
        if (explodeTime > 0) {
            isExploded = false;
            explodeTime--;
        } else if (explodeTime == 0) {
            isExploded = true;
            explodeTime = 40;
        }
        updateFlame();
    }

    public void updateFlame() {
        animation.update();
        animation_left.update();
        animation_right.update();
        animation_up.update();
        animation_down.update();
        animation_horizontal.update();
        animation_vertical.update();
    }

    private boolean isWallCollision(int xt, int yt) {
        if (TileManager.getTile(xt / 32, yt / 32) != null) {
            return TileManager.getTile(xt / 32, yt / 32) instanceof TileWall;
        }
        return false;
    }

    private boolean isBrickCollision(int xt, int yt) {
        return TileManager.getBrick(xt / 32, yt / 32) != null;
    }


    public void setPosition(int x, int y) {
        position.x = x;
        position.y = y;
    }

    public int getExplodeTime() {
        return explodeTime;
    }

    public void setExplodeTime(int explodeTime) {
        this.explodeTime = explodeTime;
    }

    public ArrayList<Rectangle> getRectangles() {
        return rectangles;
    }
}
