package com.izzist.game;

import javax.swing.JFrame;

public class Window extends JFrame {
    public Window() {
        setTitle("bomberman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(32*31,32*13));
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
