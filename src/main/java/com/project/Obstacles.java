package com.project;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Obstacles extends Entity {
    private ArrayList<Obstacle> obstacles;
    private Random random = new Random();
    private int screenWidth;
    private ObstacleFactory obstacleFactory = new ObstacleFactory();

    public Obstacles(int screenWidth) {
        this.screenWidth = screenWidth;
        this.obstacles = new ArrayList<>();
        addInitialObstacles();
    }

    public static abstract class Obstacle {
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

    public static class GroundObstacle extends Obstacle {
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

    public static class RaisedObstacle extends Obstacle {

        private int raiseHeight;
        private int stackCount;

        public RaisedObstacle(BufferedImage image, int x, int y, int raiseHeight, int stackCount) {
            super(image, x, y);
            this.raiseHeight = raiseHeight;
            this.y -= raiseHeight * stackCount;
            this.stackCount = stackCount;
        }

        @Override
        Rectangle getBounds() {
            return new Rectangle(x, y, image.getWidth(), image.getHeight() * stackCount);
        }

        @Override
        void draw(Graphics g) {
            int drawY = y;
            for (int i = 0; i < stackCount; i++) {
                g.drawImage(image, x, drawY, null);
                drawY += image.getHeight();
            }
        }
    }

    private void addInitialObstacles() {
        int x = 0;
        while (x < screenWidth * 1.5) {
            x += getRandomInterval();
            addRandomObstacle(x);
        }
    }

    private int getRandomInterval() {
        return random.nextInt(1200) + 500;
    }

    private void addRandomObstacle(int x) {
        ObstacleType type = ObstacleType.values()[random.nextInt(ObstacleType.values().length)];
        obstacles.add(obstacleFactory.createObstacle(type, x, Ground.GROUND_Y));
    }

    public void update(int speed) {
        obstacles.forEach(o -> o.x -= speed);
        obstacles.removeIf(o -> o.x + o.image.getWidth() < 0);
        if (!obstacles.isEmpty() && (obstacles.get(obstacles.size() - 1).x < screenWidth - getRandomInterval())) {
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
