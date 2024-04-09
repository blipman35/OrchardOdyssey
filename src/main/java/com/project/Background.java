package com.project;

import javax.swing.JFrame;

public class Background {
    private JFrame window = new JFrame();

    public Background() {
        window.setSize(800, 600);
        window.setBackground(java.awt.Color.BLACK); // set background color to black
        window.setTitle("Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
