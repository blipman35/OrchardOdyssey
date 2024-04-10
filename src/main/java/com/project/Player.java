package com.project;

import java.awt.*;

public class Player extends Entity{
    GameScreen gs;
    KeyInput keyI;
    private static int state;

    final int startingY = 300;
    private int speedY = 0;
    private boolean isJumping = false;

    public Player(GameScreen gs, KeyInput keyI){
        this.gs = gs;
        this.keyI = keyI;

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
            accelerate(-20);
        }else{
            //crouch logic
        }
    }
    public void draw(Graphics2D graphics2){
        graphics2.setColor(Color.white);
        graphics2.fillRect(x, y, gs.scaledTile, gs.scaledTile);
    }

    public void create(Graphics g){
       //
    }
    public static Rectangle getPlayer(){
        Rectangle player = new Rectangle();
        player.x = 100;
        /*Change with jump logic
        if(state == JUMPING && !top_y) player.y = playerTop - jumpFactor;
        else if(state == JUMPING && top_y) player.y = playerTop + jumpFactor;
        else if(state != JUMPING) dino.y = playerTop;
         */
        /*
        player.width = image.getWidth();
        player.height = image.getHeight();
         */
        return  player;
    }
}
