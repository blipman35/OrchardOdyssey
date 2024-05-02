package com.project;


import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Orchard Odyssey");

        GameScreen gameScreen = GameScreen.getInstance();
        //gameScreen.initializeEntities();
        window.add(gameScreen);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        Observer observer = new Observer("All events");
        gameScreen.attach(observer, List.of(EventType.All));

        gameScreen.startGameThread();
    }

}
