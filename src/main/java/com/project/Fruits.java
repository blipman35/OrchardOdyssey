package com.project;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Fruits extends Entity{
    class Fruit {
        BufferedImage image;
        int x;
        int y;
        boolean isHit;

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
    ArrayList<Fruits.Fruit> fruit_list;

    private Fruits.Fruit blockedAt;
    public Fruits(int initialPos){
        Random random = new Random();
        int randomNumber = random.nextInt(5000 - 1500 + 1) + 1500;

        fruit_list = new ArrayList<Fruit>();
        image_list = new ArrayList<BufferedImage>();

        initialx = initialPos;
        fruitInterval = randomNumber;
        speed = 15;


        /* input images
        -----------------------
        -----------------------
         */
        image_list.add(new Resource().getResourceImage("/images/peach.png"));
        image_list.add(new Resource().getResourceImage("/images/plum.jpg"));

        int x = initialx;
        for (BufferedImage bi : image_list){
            Fruit f = new Fruit();

            f.image = bi;
            f.x = x;
            f.y = 150;// change here
            fruit_list.add(f);
            x+=fruitInterval;

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


        if(first_f.x < -first_f.image.getWidth()){
            fruit_list.remove(first_f);
            first_f.x = fruit_list.get(fruit_list.size()-1).x + fruitInterval;
            fruit_list.add(first_f);
            first_f.isHit = false;
        }
    }

    public void create(Graphics g){
        for(Fruit f: fruit_list){
            g.setColor(Color.black);
            g.drawImage(f.image,f.x,f.y,null); //This is where we add observer!
        }
    }

    public boolean hasCollidedFruit(Player player){
        for(Fruit f: fruit_list){
            if(!f.isHit && player.getBounds().intersects(f.getFruit())){
                f.isHit = true;
                return true;
            }
        }
        return false;
    }

    public void resume(){
        int x = initialx/2;
        fruit_list = new ArrayList<Fruit>();

        for(BufferedImage bi: image_list){
            Fruit f = new Fruit();
            f.image = bi;
            f.x = x;
            f.y = 100; //Change later
            x+= fruitInterval;
            fruit_list.add(f);
        }
    }
}
