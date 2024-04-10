package com.project;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    GameScreen gs;
    KeyInput keyI;
    private static int state;

    private static int playerTop, playerBottom,topPoint;

    private static boolean topPointReached;

    private static int playerBaseY, playerTopY, playerStartX, playerEndX;

    private int foot;

    public static final int STAND_STILL = 1,
            RUNNING = 2,
            JUMPING = 3,
            DIE = 4;
    private final int LEFT_FOOT = 1,
            RIGHT_FOOT = 2,
            NO_FOOT = 3;
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

    public void create(Graphics g) {
        playerBottom = playerTop + image.getHeight();
        switch(state) {

            case STAND_STILL:
                System.out.println("stand");
                g.drawImage(image, playerStartX, playerTopY, null);
                break;

            case RUNNING:
                if(foot == NO_FOOT) {
                    foot = LEFT_FOOT;
                    g.drawImage(leftFootPlayer, playerStartX, playerTopY, null);
                } else if(foot == LEFT_FOOT) {
                    foot = RIGHT_FOOT;
                    g.drawImage(rightFootPlayer, playerStartX, playerTopY, null);
                } else {
                    foot = LEFT_FOOT;
                    g.drawImage(leftFootPlayer, playerStartX, playerTopY, null);
                }
                break;

            case JUMPING:
                if(isJumping) {
                    g.drawImage(image, playerStartX, playerTop -= 100, null);
                    break;
                }
                if(playerTop >= topPoint && !topPointReached) {
                    topPointReached = true;
                    g.drawImage(image, playerStartX, playerTop += 100, null);
                    break;
                }
                if(playerTop > topPoint && topPointReached) {
                    if(playerTopY == playerTop && topPointReached) {
                        state = RUNNING;
                        topPointReached = false;
                        break;
                    }
                    g.drawImage(image, playerStartX, playerTop += 100, null);
                    break;
                }
            case DIE:
                g.drawImage(deadPlayer, playerStartX, playerTop, null);
                break;
        }
    }

    public void die(){
        state = DIE;
    }
    public void running(){
        playerTop=playerTopY;
        topPointReached = false;
        state = JUMPING;
    }
    public static Rectangle getPlayer(){
        Rectangle player = new Rectangle();
        player.x = 100;
        if(state == JUMPING && !topPointReached) player.y = playerTop - 100;
        else if(state == JUMPING && topPointReached) player.y = playerTop + 100;
        else if(state != JUMPING) player.y = playerTop;

//        player.width = image.getWidth();
//        player.height = image.getHeight();
        player.width = 50;
        player.height = 100;
        return  player;
    }
}
