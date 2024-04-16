package com.project;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{

    GameScreen gs;
    KeyInput keyI;
    private  boolean isAlive = true;
    static BufferedImage image;
    BufferedImage leftFootPlayer;
    BufferedImage rightFootPlayer;
    BufferedImage deadPlayer;
    BufferedImage crouchPlayer;


    final int startingY = 300;
    private int speedY = 0;
    private boolean isJumping = false;
    private boolean isCrouching = false;

    public Player(GameScreen gs, KeyInput keyI){
        this.gs = gs;
        this.keyI = keyI;
        image = new Resource().getResourceImage("/images/player.png");
        leftFootPlayer = new Resource().getResourceImage("/images/player.png");
        rightFootPlayer = new Resource().getResourceImage("/images/player.png");
        deadPlayer = new Resource().getResourceImage("/images/player.png");
        crouchPlayer = new Resource().getResourceImage("/images/player.png");
        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 100;
        y =300;
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
        }
        if (keyI.downPressed && !isJumping && !isCrouching) {
            isCrouching = true;
        } else if (!keyI.downPressed && isCrouching) {
            isCrouching = false;
        }
    }

    public void draw(Graphics2D graphics2){
        //int playerBottom = Ground.GROUND_Y - image.getHeight();
        if(!isAlive){
            graphics2.drawImage(image, x, y, gs.scaledTile, gs.scaledTile, null);
        }
        else if (isCrouching) {
            graphics2.drawImage(crouchPlayer, x, y + (gs.scaledTile - gs.scaledTile / 2), gs.scaledTile, gs.scaledTile/2, null);
        }
        else {
            graphics2.drawImage(image, x, y, gs.scaledTile, gs.scaledTile, null);
        }
    }
    public void die(){
        isAlive = false;
    }

    public Rectangle getBounds() { //static
        if (isCrouching) {
            return new Rectangle(x, y + gs.scaledTile - gs.scaledTile / 2, image.getWidth()-49, image.getHeight()/2);
        }
        return new Rectangle(x, y, image.getWidth()-49, image.getHeight());
    }

}
