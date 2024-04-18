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
        player = new Player(gs, ki);
    }

    @Test
    public void testPlayer() {
        assertNotNull(player);
    }

    @Test
    public void testSetDefaultValues() {
        player.setDefaultValues();
    }

    @Test
    public void testAccelerate() {
        double acceleration = 1.0;
        player.accelerate(acceleration);
        //assertEquals(player.getSpeedY(), 1.0);
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
