package com.project;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Fruits extends Entity{
    private class Fruit {
        BufferedImage image;
        int x;
        int y;

        Rectangle getFruit() {
            Rectangle fruit = new Rectangle();
            fruit.x = x;
            fruit.y = y;
            fruit.width = image.getWidth();
            fruit.height = image.getHeight();

            return fruit;
        }
    }
    private int initialx;
    private int fruitInterval;
    private int speed;

    private ArrayList<BufferedImage> image_list;
    private ArrayList<Fruits.Fruit> fruit_list;

    private Fruits.Fruit blockedAt;
    public Fruits(int initialPos){
        fruit_list = new ArrayList<Fruits.Fruit>();
        image_list = new ArrayList<BufferedImage>();

        initialx = initialPos;
        fruitInterval = 200;
        speed = 7;


        /* input images
        -----------------------
        -----------------------
         */

        int x = initialx;
        for (BufferedImage bi : image_list){
            Fruits.Fruit f = new Fruits.Fruit();

            f.image = bi;
            f.x = x;
            f.y = 100;// change here

        }
    }

    public void update(){
        Iterator<Fruits.Fruit> frloop = fruit_list.iterator();
        Fruits.Fruit first_f = frloop.next();
        first_f.x -= speed;

        while(frloop .hasNext()){
            Fruits.Fruit f = frloop.next();
            f.x -= speed;
        }

        Fruits.Fruit last_f = fruit_list.get(fruit_list.size()-1);

        if(first_f.x < -first_f.image.getWidth()){
            fruit_list.remove(first_f);
            first_f.x = fruit_list.get(fruit_list.size()-1).x + fruitInterval;
            fruit_list.add(first_f);
        }
    }

    public void create(Graphics g){
        for(Fruits.Fruit f: fruit_list){
            g.setColor(Color.black);
            g.drawImage(f.image,f.x,f.y,null); //This is where we add observer!
        }
    }

    public boolean hasCollidedFruit(){
        for(Fruits.Fruit f: fruit_list){
            if(Player.getPlayer().intersects(f.getFruit())){
                System.out.println("Collision has occurred");
                blockedAt = f;
                return true;
            }
        }
        return false;
    }

    public void resume(){
        int x = initialx/2;
        fruit_list = new ArrayList<Fruits.Fruit>();

        for(BufferedImage bi: image_list){
            Fruits.Fruit f = new Fruits.Fruit();
            f.image = bi;
            f.x = x;
            f.y = 100; //Change later
            x+= fruitInterval;
            fruit_list.add(f);
        }
    }
}
