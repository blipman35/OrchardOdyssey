package com.project;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Background {

    private class BackgroundImage {
        BufferedImage image;
        int x;
    }

    private BufferedImage image;

    private ArrayList<BackgroundImage> BackgroundImageSet;

    private int width;
    public Background(int panelHeight, int panelWidth) {

        try{
            image = new Resource().getResourceImage("/images/Sky.png");
        } catch(Exception e) {e.printStackTrace();}

        BackgroundImageSet = new ArrayList<BackgroundImage>();

        int numImages = (int) Math.ceil((double) panelWidth / image.getWidth()) + 1;
        BackgroundImageSet.clear();
        for (int i = 0; i < numImages; i++) {
            BackgroundImage obj = new BackgroundImage();
            obj.image = image;
            obj.x = i * image.getWidth();
            BackgroundImageSet.add(obj);
        }
        this.width = panelWidth;
    }

    public void update(int speed) {
        Iterator<BackgroundImage> looper = BackgroundImageSet.iterator();
        BackgroundImage first = looper.next();

        first.x -= speed;

        int previousX = first.x;
        while(looper.hasNext()) {
            BackgroundImage next = looper.next();
            next.x = previousX + image.getWidth();
            previousX = next.x;
        }

        if(first.x < -image.getWidth()) {
            BackgroundImageSet.remove(first);
            first.x = previousX + image.getWidth();
            BackgroundImageSet.add(first);
        }
    }

    public void create(Graphics g) {
        for(BackgroundImage img : BackgroundImageSet) {
            g.drawImage(img.image, img.x, Ground.GROUND_Y - img.image.getHeight(), null);
        }
    }

}
