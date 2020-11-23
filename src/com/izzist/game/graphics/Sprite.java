package com.izzist.game.graphics;

import java.awt.image.BufferedImage;

public class Sprite extends SpriteSheet{

    private BufferedImage[][] spriteArray;

    private int spritePerWidth;
    private int spriteHeight;


    public Sprite(String path, int tileSizeWidth, int tileSizeHeight) {

        super(path,tileSizeWidth,tileSizeHeight);
        spriteSheet = loadSprite(path);
        spritePerWidth = spriteSheet.getWidth() / tileSizeWidth;
        spriteHeight = spriteSheet.getHeight() / tileSizeWidth;
        loadSpriteArray();
    }

    public void loadSpriteArray() {
        spriteArray = new BufferedImage[spriteHeight][spritePerWidth];

        for (int i = 0; i < spriteHeight; i++) {
            for (int j = 0; j < spritePerWidth; j++) {
                spriteArray[i][j] = getSprite(j, i);
            }
        }
    }

    public BufferedImage getSprite(int x, int y) {
        return spriteSheet.getSubimage(x*tileSizeWidth, y*tileSizeHeight, tileSizeWidth, tileSizeHeight);
    }

    public BufferedImage[] getSpriteArray(int position, int characterNumber) {
        BufferedImage[] spriteArray2 = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            spriteArray2[i] = spriteArray[position * 3 + i][characterNumber];
        }
        return spriteArray2;
    }

    public BufferedImage[] getSpriteArray2(int position) {
        return spriteArray[position];
    }

}
