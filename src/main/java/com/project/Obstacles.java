package com.project;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Obstacles extends Entity{
    private class Obstacle {
        BufferedImage image;
        int x;
        int y;
        String imageName = "Scarecrow";

        Rectangle getObstacle() {
            Rectangle obstacle = new Rectangle();
            obstacle.x = x;
            if ("Crow".equals(imageName)) {
                obstacle.y = y - 45;  // Since it is raised, decrease the y position
            } else {
                obstacle.y = y;
            }
            obstacle.width = image.getWidth();
            obstacle.height = image.getHeight();
            return obstacle;
        }
    }
    private int initialx;
    private int obstacleInterval;

    private ArrayList<BufferedImage> image_list;
    private ArrayList<Obstacle> obstacle_list;

    private Obstacle blockedAt;
    public Obstacles(int initialPos){
        obstacle_list = new ArrayList<Obstacle>();
        image_list = new ArrayList<BufferedImage>();

        Random random = new Random();
        int randomNumber = random.nextInt(2600 - 500 + 1) + 500;
        initialx = initialPos;
        obstacleInterval = randomNumber;
        //speed = 10;

        image_list.add(new Resource().getResourceImage("/images/scarecrow-1.jpg"));
        image_list.add(new Resource().getResourceImage("/images/scarecrow-1.jpg"));
        image_list.add(new Resource().getResourceImage("/images/crow.png"));

        /* input images
        -----------------------
        -----------------------
         */

        int x = initialx;
        for (BufferedImage bi : image_list){
            Obstacle o = new Obstacle();
            if (bi.getType() == 13){
                o.imageName = "Crow";
            }
            o.image = bi;
            o.x = x;
            o.y = y;
            obstacle_list.add(o);
            x+= obstacleInterval;
        }
    }

    public void update(int speed){
        Iterator<Obstacle> obloop = obstacle_list.iterator();
        Obstacle first_o = obloop.next();
        first_o.x -= speed;

        while(obloop .hasNext()){
            Obstacle o = obloop.next();
            o.x -= speed;
        }


        if(first_o.x < -first_o.image.getWidth()){
            obstacle_list.remove(first_o);
            first_o.x = obstacle_list.get(obstacle_list.size()-1).x + obstacleInterval;
            obstacle_list.add(first_o);
        }
    }

    public void create(Graphics g){
        for(Obstacle o: obstacle_list){
            int obstacleBottom = Ground.GROUND_Y - o.image.getHeight()+5;
            if (o.imageName.equals("Crow")) {
                obstacleBottom -= 45; // Raise the image by 50 pixels
            }
            g.drawImage(o.image,o.x,obstacleBottom,null); //This is where we add observer!
        }
    }

    public boolean hasCollidedObstacle(Player player){
        Rectangle playerBounds = player.getBounds();
        for(Obstacle o: obstacle_list){
            if(playerBounds.intersects(o.getObstacle())){
                System.out.println("Collision has occurred");
                GameScreen.getInstance().notifyObservers(EventType.Collision, "Collision has occurred");
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
            o.y = 150; //Change later
            x+= obstacleInterval;
            obstacle_list.add(o);
        }
    }
}
