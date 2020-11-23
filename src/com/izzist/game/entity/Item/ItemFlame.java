package com.izzist.game.entity.Item;

import com.izzist.game.graphics.Animation;
import com.izzist.game.ultility.Vector2D;

import java.awt.*;

public class ItemFlame extends Item{
    public ItemFlame(Vector2D position){
        this.position = position;
        animation = new Animation();
        setAnimation2(sprite.getSpriteArray2(1),8 );
    }

    @Override
    public void render(Graphics2D g2D) {
        g2D.drawImage(animation.getImage(), (int) (position.x), (int) (position.y), size, size, null);
    }

    @Override
    public void update() {
        animation.update();
    }
}