package com.project;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel implements Runnable{
   final int originalTile = 16;
   final int scale = 3;
   final int scaledTile = originalTile * scale;
   final int maxScreenCol = 16;
   final int maxScreenRow = 12;
   final int screenWidth = scaledTile * maxScreenCol;
   final int screenHeight = scaledTile * maxScreenRow;

   int FPS = 60;
   KeyInput keyI = new KeyInput();
   Thread gameThread;
    public  GameScreen(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.cyan);
        this.setDoubleBuffered(true);
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
        if(keyI.upPressed == true){
            //jump logic
        }else{
            //crouch logic
        }
    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2 = (Graphics2D) graphics;

        graphics2.setColor(Color.white);
        graphics2.fillRect(100,100, scaledTile, scaledTile);
        graphics2.dispose();
    }
}
