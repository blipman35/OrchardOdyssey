package com.project;

import java.awt.*;

public class Player extends Entity{
    GameScreen gs;
    KeyInput keyI;

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
}
