package com.project;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Obstacles extends Entity {
    abstract class Obstacle {
        protected BufferedImage image;
        protected int x;
        protected int y;

        public Obstacle(BufferedImage image, int x, int y) {
            this.image = image;
            this.x = x;
            this.y = y - image.getHeight();
        }

        abstract Rectangle getBounds();
        abstract void draw(Graphics g);
    }

    class GroundObstacle extends Obstacle {
        public GroundObstacle(BufferedImage image, int x, int y) {
            super(image, x, y);
        }

        @Override
        Rectangle getBounds() {
            return new Rectangle(x, y, image.getWidth(), image.getHeight());
        }

        @Override
        void draw(Graphics g) {
            g.drawImage(image, x, y, null);
        }
    }

    class RaisedObstacle extends Obstacle {
        private int raiseHeight;

        public RaisedObstacle(BufferedImage image, int x, int y, int raiseHeight) {
            super(image, x, y);
            this.raiseHeight = raiseHeight;
            this.y -= raiseHeight;
        }

        @Override
        Rectangle getBounds() {
            return new Rectangle(x, y, image.getWidth(), image.getHeight());
        }

        @Override
        void draw(Graphics g) {
            g.drawImage(image, x, y, null);
        }
    }

    private ArrayList<Obstacle> obstacles;
    private int initialX;
    private int obstacleInterval;

    public Obstacles(int initialPos) {
        obstacles = new ArrayList<>();
        Random random = new Random();
        initialX = initialPos;
        obstacleInterval = random.nextInt(2101) + 500;

        // Load images
        BufferedImage crowImage = new Resource().getResourceImage("/images/crow.png");
        BufferedImage shortImage = new Resource().getResourceImage("/images/Haystack-short.png");
        BufferedImage tallImage = new Resource().getResourceImage("/images/Haystack-tall.png");


        // Initialize obstacles
        int x = initialX;
        obstacles.add(new GroundObstacle(shortImage, x, Ground.GROUND_Y));
        x += obstacleInterval;
        obstacles.add(new GroundObstacle(tallImage, x, Ground.GROUND_Y));
        x += obstacleInterval;
        obstacles.add(new RaisedObstacle(crowImage, x, Ground.GROUND_Y, 45));

    }

    public void update(int speed){
        Iterator<Obstacle> obloop = obstacles.iterator();
        Obstacle first_o = obloop.next();
        first_o.x -= speed;

        while(obloop .hasNext()){
            Obstacle o = obloop.next();
            o.x -= speed;
        }

        if(first_o.x < -first_o.image.getWidth()){
            obstacles.remove(first_o);
            first_o.x = obstacles.get(obstacles.size()-1).x + obstacleInterval;
            obstacles.add(first_o);
        }
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public void create(Graphics g) {
        for (Obstacle o : obstacles) {
            o.draw(g);
        }
    }

    public boolean hasCollidedObstacle(Player player) {
        Rectangle playerBounds = player.getBounds();
        for (Obstacle o : obstacles) {
            if (playerBounds.intersects(o.getBounds())) {
                System.out.println("Collision has occurred");
                GameScreen.getInstance().notifyObservers(EventType.Collision, "Collision has occurred");
                return true;
            }
        }
        return false;
    }
}
