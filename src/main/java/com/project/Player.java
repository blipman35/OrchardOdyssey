package com.project;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    private int x, y;
    private BufferedImage player_1, player_2, player_3, player_4, player_jump;
    private boolean isJumping = false, isCrouching = false, isAlive = true;
    private int spriteCounter, spriteNum;
    private final int startingY = 288;
    private int speedY = 0;
    private KeyInput keyI;
    private GameScreen gs;

    /* The Builder pattern creates an immutable player and encapsulates the
    construction logic within the Builder class. This also allows for flexible
    player creation as the parameters are built out step-by-step
     */

    private Player(Builder builder) {
        this.gs = builder.gs;
        this.keyI = builder.keyI;
        this.x = builder.x;
        this.y = startingY;
        getPlayerImages();

    }

    public static class Builder {
        private int x=100, y= build().startingY, speed;
        private KeyInput keyI;
        private GameScreen gs;

        public Builder(GameScreen gs, KeyInput keyI) {
            this.gs = gs;
            this.keyI = keyI;
        }

        public Builder setX(int x) {
            this.x = x;
            return this;
        }

        public Builder setY(int y) {
            this.y = y;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }

    public void getPlayerImages() {
        try {
            player_1 = ImageIO.read(getClass().getResource("/player/player_1.png"));
            player_2 = ImageIO.read(getClass().getResource("/player/player_2.png"));
            player_3 = ImageIO.read(getClass().getResource("/player/player_3.png"));
            player_4 = ImageIO.read(getClass().getResource("/player/player_4.png"));
            player_jump = ImageIO.read(getClass().getResource("/player/player_jump.png"));
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
            if (isCrouching) { isCrouching = false; }
            isJumping = true;
            accelerate(-22);
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
        else if(isJumping) {
            graphics2.drawImage(player_jump, x, y, gs.scaledTile, gs.scaledTile, null);
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
        return new Rectangle(x, y, player_1.getWidth(), player_1.getHeight()-50);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getSpeedY() {
        return speedY;
    }

}
