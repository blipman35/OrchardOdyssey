package com.project;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Obstacles extends Entity {
    abstract class Obstacle {
        protected BufferedImage image;
        protected int x, y;

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
    private Random random = new Random();
    private int screenWidth;
    private final Resource resource = new Resource();

    public Obstacles(int screenWidth) {
        this.screenWidth = screenWidth;
        this.obstacles = new ArrayList<>();
        addInitialObstacles();
    }

    private void addInitialObstacles() {
        int x = 0;
        while (x < screenWidth * 1.5) {  // Fill the initial screen and some extra
            x += getRandomInterval();
            addRandomObstacle(x);
        }
    }

    private int getRandomInterval() {
        return random.nextInt(1200) + 500;  // Variable intervals between 200 and 400 pixels
    }

    private void addRandomObstacle(int x) {
        int type = random.nextInt(3);
        switch (type) {
            case 0:
                obstacles.add(new GroundObstacle(resource.getResourceImage("/images/Haystack-short.png"), x, Ground.GROUND_Y));
                break;
            case 1:
                obstacles.add(new GroundObstacle(resource.getResourceImage("/images/Haystack-tall.png"), x, Ground.GROUND_Y));
                break;
            case 2:
                obstacles.add(new RaisedObstacle(resource.getResourceImage("/images/crow.png"), x, Ground.GROUND_Y, 45));
                break;
        }
    }

    public void update(int speed){
        // Move obstacles
        obstacles.forEach(o -> o.x -= speed);
        // Remove obstacles that have scrolled off the screen
        obstacles.removeIf(o -> o.x + o.image.getWidth() < 0);
        // Add new obstacles if needed
        if (obstacles.isEmpty() || obstacles.get(obstacles.size() - 1).x < screenWidth - getRandomInterval()) {
            addRandomObstacle(obstacles.get(obstacles.size() - 1).x + getRandomInterval());
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
                GameScreen.getInstance().notifyObservers(EventType.Collision, "Collision has occurred");
                return true;
            }
        }
        return false;
    }
}
