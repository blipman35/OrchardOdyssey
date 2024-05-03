package com.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TestObstacles {


    private Obstacles obstacles;
    private Player player;
    private int screenWidth = 100;
    private Resource resource = new Resource();

    @BeforeEach
    public void setUp() {
        GameScreen gs = GameScreen.getInstance();
        KeyInput ki = new KeyInput();
        player = new Player.Builder(gs, ki).setX(100).setY(288).build();
        obstacles = new Obstacles(screenWidth);
    }

    @Test
    public void testObstaclesInitialization() {
        assertNotNull(obstacles, "Obstacles should not be null after initialization.");
    }

    @Test
    public void testUpdateWithPositiveSpeed() {
        int initialSize = obstacles.getObstacles().size();
        int speed = 10;
        obstacles.update(speed);
        assertTrue(initialSize >= obstacles.getObstacles().size(), "Obstacle count should not increase after update.");
    }

    @Test
    public void testCollisionDetectionNoCollision() {
        assertFalse(obstacles.hasCollidedObstacle(player), "No collision should be detected when player is not intersecting with any obstacles.");
    }


}
