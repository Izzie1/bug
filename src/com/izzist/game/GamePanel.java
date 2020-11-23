package com.izzist.game;

import com.izzist.game.states.GameStateManager;
import com.izzist.game.ultility.KeyHandler;

import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    public static int width;
    public static int height;
    private boolean running = true;
    private Thread thread;
    private BufferedImage image;
    private Graphics2D g2D;
    private KeyHandler key;
    private GameStateManager gameStateManager;

    public GamePanel(int w, int h) {
        this.width = w;
        this.height = h;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();

        if (thread == null) {
            thread = new Thread(this, "bomberman");
            thread.start();
        }
    }

    public void init() {
        running = true;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2D = (Graphics2D) image.getGraphics();
        key = new KeyHandler(this);
        gameStateManager = new GameStateManager();
    }

    @Override
    public void run() {
        init();
        int unit = 1000000000;
        final double Game_Hertz = 60;
        final double timeBeforeUpdate = unit / Game_Hertz;
        final int mustUpdateBeforeRender = 5;

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 60;
        final double totalTimeBeforeRender = unit / TARGET_FPS;

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / unit);

        while (running) {
            double time = System.nanoTime();
            int updateCount = 0;
            while (((time - lastUpdateTime) > timeBeforeUpdate) &&
                    updateCount < mustUpdateBeforeRender) {
                update();
                input(key);
                lastUpdateTime += timeBeforeUpdate;
                updateCount++;
            }

            if (time - lastUpdateTime > timeBeforeUpdate) {
                lastUpdateTime = time - timeBeforeUpdate;
            }

            lastRenderTime = time;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime / unit);
            if (thisSecond > lastSecondTime) {
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while (time - lastRenderTime < totalTimeBeforeRender
                    && time - lastUpdateTime < timeBeforeUpdate) {
                thread.yield();
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    System.out.print("ERROR:yielding thread");
                }
                time = System.nanoTime();
            }

            input(key);
            render();
            paint();
        }
    }

    private void render() {
        if (g2D != null) {
            g2D.setColor(new Color(66, 134, 244));
            g2D.fillRect(0, 0, width, height);
            gameStateManager.render(g2D);
        }
    }

    private void paint() {
        Graphics g = (Graphics) this.getGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
    }


    private void update() {
        gameStateManager.update();

    }

    public void input(KeyHandler key) {
        gameStateManager.input(key);
    }
}


