package com.izzist.game.graphics;

import java.awt.image.BufferedImage;

public class Animation {
    private BufferedImage[] frames;
    private int currentFrame;
    public int numFrames;
    private int timePlay;
    private int count;
    private int delay;

    public Animation() {
    }

    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
        currentFrame = 0;
        timePlay=0;
        count = 0;
        delay = 2;
        numFrames = frames.length;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void setNumFrames(int numFrames) {
        this.numFrames = numFrames;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void update() {
        if (delay == -1) {
            return;
        }

        count++;

        if (count == delay) {
            currentFrame++;
            count = 0;
        }
        if (currentFrame == numFrames) {
            currentFrame = 0;
            timePlay++;
        }
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public int getCount() {
        return count;
    }

    public int getDelay() {
        return delay;
    }

    public BufferedImage getImage() {
        return frames[currentFrame];
    }

    public boolean playOnce(){
        return timePlay > 0;
    }
}
