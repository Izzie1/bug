package com.izzist.game.entity.Enemy;

import com.izzist.game.entity.Character;
import com.izzist.game.states.PlayState;

import java.awt.*;
import java.util.Random;

public abstract class Enemy extends Character {
    protected int random;
    protected int randomSpeed = 200;
    Rectangle rectangle;
    public Enemy(){

    }
    public void randomDirection() {
        if (randomSpeed > 0) {
            randomSpeed--;
        } else {
            random = new Random().nextInt(4);
            randomSpeed = 200;
        }
    }



}
