package com.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TestObstacles {

    private Obstacles obstacles;
    private Player player;

    @BeforeEach
    public void setUp() {
        GameScreen gs = GameScreen.getInstance();
        KeyInput ki = new KeyInput();
        player = new Player.Builder(gs, ki).setX(100).setSpeed(5).build();
        obstacles = new Obstacles(100); }

    @Test
    public void testObstacles() { assertNotNull(obstacles); }

    @Test
    public void testUpdate() { int speed = 10; obstacles.update(speed); }

    @Test
    public void testCreate() { assert true; }

    @Test
    public void hasCollidedObstacle() {
        assertTrue(obstacles.hasCollidedObstacle(player));
    }

}
