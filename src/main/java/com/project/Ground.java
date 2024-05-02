package com.project;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Ground {

    private class GroundImage {
        BufferedImage image;
        int x;
    }

    public static int GROUND_Y;

    private BufferedImage image;

    private ArrayList<GroundImage> groundImageSet;

    private int width;
    public Ground(int panelHeight, int panelWidth) {

        GROUND_Y = 336;

        try{
            image = new Resource().getResourceImage("/images/test.png");
        } catch(Exception e) {e.printStackTrace();}

        groundImageSet = new ArrayList<GroundImage>();

        int numImages = (int) Math.ceil((double) panelWidth / image.getWidth()) + 1;
        groundImageSet.clear();
        for (int i = 0; i < numImages; i++) {
            GroundImage obj = new GroundImage();
            obj.image = image;
            obj.x = i * image.getWidth();
            groundImageSet.add(obj);
        }
        this.width = panelWidth;
    }

    public void update(int speed) {
        Iterator<GroundImage> looper = groundImageSet.iterator();
        GroundImage first = looper.next();

        first.x -= speed;

        int previousX = first.x;
        while(looper.hasNext()) {
            GroundImage next = looper.next();
            next.x = previousX + image.getWidth();
            previousX = next.x;
        }

        if(first.x < -image.getWidth()) {
            groundImageSet.remove(first);
            first.x = previousX + image.getWidth();
            groundImageSet.add(first);
        }
    }

    public void create(Graphics g) {
        for(GroundImage img : groundImageSet) {
            g.drawImage(img.image, img.x, GROUND_Y, null);
        }
    }

    public Rectangle getGroundBounds() {
        // Assuming the ground covers the entire screen width and the height of the ground is the height of the image
        return new Rectangle(0, GROUND_Y, width, image.getHeight());
    }

}
