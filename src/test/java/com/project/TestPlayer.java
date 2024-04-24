package com.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


public class TestPlayer {

    private Player player;

    @BeforeEach
    public void setUp() {
        GameScreen gs = GameScreen.getInstance();
        KeyInput ki = new KeyInput();
        player = new Player.Builder(gs, ki).setX(100).setY(300).setSpeed(5).build();
    }

    @Test
    public void testPlayer() {
        assertNotNull(player);
    }

    @Test
    public void testDefaultValues() {
        assertEquals(100, player.getX());
        assertEquals(300, player.getY());
    }

    @Test
    void testAccelerate() {
        double initialSpeedY = player.getSpeedY();
        double accelerationY = 10;
        player.accelerate(accelerationY);
        assertEquals(initialSpeedY + accelerationY, player.getSpeedY());
    }

    @Test
    void testMove() {
        double initialY = player.getY();
        double yDelta = 5;
        player.move(yDelta);
        assertEquals(initialY + yDelta, player.getY());
    }

    @Test
    public void testUpdate() {
        player.update();
    }
    @Test
    public void testDraw() {
        assert true;
    }

    @Test
    public void testDie() { player.die(); }

    @Test
    public void testGetBounds() {
        assertNotNull(player.getBounds());
    }
}
