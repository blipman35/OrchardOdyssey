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
        while(gameThread != null){
            //Update game info
            update();
            //Draw screen with updated information
            repaint();
        }
    }

    public void update(){

    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2 = (Graphics2D) graphics;

        graphics2.setColor(Color.white);
        graphics2.fillRect(100,100, scaledTile, scaledTile);
        graphics2.dispose();
    }
}
