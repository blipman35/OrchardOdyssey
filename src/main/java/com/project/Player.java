package com.project;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{

    GameScreen gs;
    KeyInput keyI;
    private  boolean isAlive;
    static BufferedImage image;
    BufferedImage leftFootPlayer;
    BufferedImage rightFootPlayer;
    BufferedImage deadPlayer;


    final int startingY = 300;
    private int speedY = 0;
    private boolean isJumping = false;

    public Player(GameScreen gs, KeyInput keyI){
        this.gs = gs;
        this.keyI = keyI;
        image = new Resource().getResourceImage("/images/player.png");
        leftFootPlayer = new Resource().getResourceImage("/images/player.png");
        rightFootPlayer = new Resource().getResourceImage("/images/player.png");
        deadPlayer = new Resource().getResourceImage("/images/player.png");
        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 100;
        y = 300;
        speed = 4;
    }
    public void accelerate(double accelerationY) {
        speedY += accelerationY;
    }

    public void move(double yDelta) {
        y += yDelta;
        // do collision detection here. upon collision, set speedX/speedY to zero..!
    }

    public void update(){
        if (y > startingY) {
            speedY = 0;
            y = startingY;
            isJumping = false;
        }

        if(speedY != 0 || y < startingY){
            move(speedY);
            accelerate(1);
        }

        if(keyI.upPressed && !isJumping){
            isJumping = true;;
            accelerate(-25);
        }else{
            //crouch logic
        }
    }

    public void draw(Graphics2D graphics2){
        if(!isAlive){
            graphics2.drawImage(image, x, y, gs.scaledTile, gs.scaledTile, null);
        }
        graphics2.drawImage(image, x, y, gs.scaledTile, gs.scaledTile, null);
    }
    public void die(){
        isAlive = false;
    }

    public static Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth()-50, image.getHeight()-50);
    }

}
