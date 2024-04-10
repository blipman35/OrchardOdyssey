package com.project;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Obstacles extends Entity{
    private class Obstacle {
        BufferedImage image;
        int x;
        int y;

        Rectangle getObstacle() {
            Rectangle obstacle = new Rectangle();
            obstacle.x = x;
            obstacle.y = y;
            obstacle.width = image.getWidth();
            obstacle.height = image.getHeight();

            return obstacle;
        }
    }
    private int initialx;
    private int obstacleInterval;
    private int speed;

    private ArrayList<BufferedImage> image_list;
    private ArrayList<Obstacle> obstacle_list;

    private Obstacle blockedAt;
    public Obstacles(int initialPos){
        obstacle_list = new ArrayList<Obstacle>();
        image_list = new ArrayList<BufferedImage>();

        initialx = initialPos;
        obstacleInterval = 200;
        speed = 7;


        /* input images
        -----------------------
        -----------------------
         */

        int x = initialx;
        for (BufferedImage bi : image_list){
            Obstacle o = new Obstacle();

            o.image = bi;
            o.x = x;
            o.y = 100;// change here

        }
    }

    public void update(){
        Iterator<Obstacle> obloop = obstacle_list.iterator();
        Obstacle first_o = obloop.next();
        first_o.x -= speed;

        while(obloop .hasNext()){
            Obstacle o = obloop.next();
            o.x -= speed;
        }

        Obstacle last_o = obstacle_list.get(obstacle_list.size()-1);

        if(first_o.x < -first_o.image.getWidth()){
            obstacle_list.remove(first_o);
            first_o.x = obstacle_list.get(obstacle_list.size()-1).x + obstacleInterval;
            obstacle_list.add(first_o);
        }
    }

    public void create(Graphics g){
        for(Obstacle o: obstacle_list){
            g.setColor(Color.black);
            g.drawImage(o.image,o.x,o.y,null); //This is where we add observer!
        }
    }

    public boolean hasCollidedObstacle(){
        for(Obstacle o: obstacle_list){
            if(Player.getPlayer().intersects(o.getObstacle())){
                System.out.println("Collision has occurred");
                blockedAt = o;
                return true;
            }
        }
        return false;
    }

    public void resume(){
        int x = initialx/2;
        obstacle_list = new ArrayList<Obstacle>();

        for(BufferedImage bi: image_list){
            Obstacle o = new Obstacle();
            o.image = bi;
            o.x = x;
            o.y = 100; //Change later
            x+= obstacleInterval;
            obstacle_list.add(o);
        }
    }
}
