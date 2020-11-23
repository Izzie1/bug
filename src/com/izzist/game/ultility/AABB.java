package com.izzist.game.ultility;

import com.izzist.game.managers.TileManager;

public class AABB {
    private int width;
    private int height;
    public Vector2D position;
    private int xOffset;
    private int yOffset;

    public AABB(int width, int height, Vector2D position, int xOffset, int yOffset) {
        this.width = width;
        this.height = height;
        this.position = position;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }




    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
