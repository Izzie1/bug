package com.izzist.game.entity.Enemy;

import com.izzist.game.graphics.Animation;
import com.izzist.game.graphics.Sprite;
import com.izzist.game.ultility.AABB;
import com.izzist.game.ultility.Vector2D;

import java.awt.*;

public class Balloom extends Enemy {

    public Balloom(Vector2D position, int size) {
        this.position = position;
        this.size = size;
        this.sprite = new Sprite("font/Balloom2.png", 16, 16);
        animation = new Animation();
        setAnimation(0, sprite.getSpriteArray2(0), 5);
        bounds = new AABB(28, 28, this.position, 2, 2);
        rectangle = new Rectangle((int) bounds.position.x + bounds.getxOffset(),
                (int) bounds.position.y + bounds.getyOffset(), 28, 28);
        speed = 0.5f;
    }

    public void animate() {
        if (random == 0) {
            if (currentAnimation != 0) {
                setAnimation(0, sprite.getSpriteArray2(0), 10);
            }
        } else if (random == 1) {
            if (currentAnimation != 1) {
                setAnimation(1, sprite.getSpriteArray2(1), 10);
            }
        } else if (random == 2) {
            if (currentAnimation != 1) {
                setAnimation(1, sprite.getSpriteArray2(1), 10);
            }
        } else if (random == 3) {
            if (currentAnimation != 0) {
                setAnimation(0, sprite.getSpriteArray2(0), 10);
            }
        }
    }

    public void move() {
        if (random == 0) {
            dy = -speed;
        }
        if (random == 1) {
            dy = speed;
        }
        if (random == 2) {
            dx = -speed;
        }
        if (random == 3) {
            dx = speed;
        }
    }

    @Override
    public void render(Graphics2D g2D) {
        g2D.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.y);
        g2D.drawImage(animation.getImage(), (int) (position.x), (int) (position.y), size, size, null);
    }

    @Override
    public void update() {

        animation.update();
        move();
        animate();
        randomDirection();
        if (!collision(dx, 0)) {
            position.x += dx;
        }
        if (!collision(0, dy)) {
            position.y += dy;
        }

    }

}
