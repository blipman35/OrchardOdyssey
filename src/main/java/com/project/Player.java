package com.project;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GameScreen gs;
    KeyInput keyI;
    private  boolean isAlive = true;
    BufferedImage deadPlayer;

    BufferedImage player_1, player_2, player_3, player_4;
    final int startingY = 300;
    private int speedY = 0;
    private boolean isJumping = false;
    private boolean isCrouching = false;

    private int spriteCounter = 0;
    private int spriteNum = 0;

    public Player(GameScreen gs, KeyInput keyI){
        this.gs = gs;
        this.keyI = keyI;
        getPlayerImages();
        deadPlayer = new Resource().getResourceImage("/images/player.png");
        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 100;
        y =300;
        speed = 4;
    }

    public void getPlayerImages() {

        try {
            player_1 = ImageIO.read(getClass().getResource("/player/player_1.png"));
            player_2 = ImageIO.read(getClass().getResource("/player/player_2.png"));
            player_3 = ImageIO.read(getClass().getResource("/player/player_3.png"));
            player_4 = ImageIO.read(getClass().getResource("/player/player_4.png"));

        }
        catch(IOException e) {
            e.printStackTrace();
        }
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

        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNum = (spriteNum + 1) % 4;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D graphics2){
        //int playerBottom = Ground.GROUND_Y - image.getHeight();
        BufferedImage image = null;
        if (spriteNum == 1) {
            image = player_1;
        } else if (spriteNum == 2) {
            image = player_2;
        } else if (spriteNum == 3) {
            image = player_3;
        } else {
            image = player_4;
        }
        if(!isAlive){
            // still need a dead player
            graphics2.drawImage(image, x, y, gs.scaledTile, gs.scaledTile, null);
        }
        else if (isCrouching) {
            // still need a crouching player
            graphics2.drawImage(image, x, y + (gs.scaledTile - gs.scaledTile / 2), gs.scaledTile, gs.scaledTile/2, null);
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
            return new Rectangle(x, y + gs.scaledTile - gs.scaledTile / 2, player_1.getWidth(), player_1.getHeight()/2);
        }
        return new Rectangle(x, y, player_1.getWidth(), player_1.getHeight());
    }

}
