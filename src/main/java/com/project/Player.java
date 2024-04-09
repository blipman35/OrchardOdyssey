package com.project;

import java.awt.*;

public class Player extends Entity{
    GameScreen gs;
    KeyInput keyI;

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
}
