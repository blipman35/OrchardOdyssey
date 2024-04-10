package com.project;

import java.awt.*;

public class Player extends Entity{
    GameScreen gs;
    KeyInput keyI;
    private static int state;

    public Player(GameScreen gs, KeyInput keyI){
        this.gs = gs;
        this.keyI = keyI;

        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update(){
        if(keyI.upPressed == true){
            //jump logic
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
