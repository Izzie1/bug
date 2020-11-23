package com.izzist.game.entity.Item;

import com.izzist.game.entity.Entity;
import com.izzist.game.graphics.Sprite;
import com.izzist.game.ultility.Vector2D;

import java.awt.*;

public abstract class Item extends Entity {

    public Item(){
        this.sprite = new Sprite("font/items_16_16.png",16,16);
        this.size = 24;
    }
}
