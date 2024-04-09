package com.project;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel implements Runnable{
   final int originalTile = 16;
   final int scale = 3;
   public final int scaledTile = originalTile * scale;
   final int maxScreenCol = 16;
   final int maxScreenRow = 12;
   final int screenWidth = scaledTile * maxScreenCol;
   final int screenHeight = scaledTile * maxScreenRow;

   int FPS = 60;
   KeyInput keyI = new KeyInput();
   Thread gameThread;
   Player player = new Player(this,keyI);
   int playerSpeed = 4;
    public  GameScreen(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.cyan);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyI);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update(){
        player.update();
    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2 = (Graphics2D) graphics;

        player.draw(graphics2);
        graphics2.dispose();
    }
}
