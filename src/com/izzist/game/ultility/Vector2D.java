package com.izzist.game.ultility;

public class Vector2D {
    public float x;
    public float y;


    public Vector2D() {
        x = 0;
        y = 0;
    }

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D pos) {
        new Vector2D(pos.x, pos.y);
    }


    public void setVector(Vector2D vector2D) {
        this.x = vector2D.x;
        this.y = vector2D.y;
    }

    public void setVector(float x, float y) {
        this.x = x;
        this.y = y;
    }



    @Override
    public String toString() {
        return x + " , " + y;
    }
}
