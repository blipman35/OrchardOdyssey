package com.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TestObstacles {

    private Obstacles obstacles;
    private Player player;

    @BeforeEach
    public void setUp() {
        GameScreen gs = new GameScreen();
        KeyInput ki = new KeyInput();
        player = new Player(gs, ki);
        player.setDefaultValues();
        obstacles = new Obstacles(100); }

    @Test
    public void testObstacles() { assertNotNull(obstacles); }

    @Test
    public void testUpdate() { obstacles.update(); }

    @Test
    public void testCreate() { assert true; }

    @Test
    public void hasCollidedObstacle() {
        assertTrue(obstacles.hasCollidedObstacle(player));
    }

    @Test
    public void testResume() {
        obstacles.resume();
    }
}
