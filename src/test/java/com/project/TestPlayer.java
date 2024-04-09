package com.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


public class TestPlayer {

    private Player player;

    @BeforeEach
    public void setUp() {
        GameScreen gs = new GameScreen();
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
    public void testUpdate() {
        assert true;
    }

    @Test
    public void testDraw() {
        assert true;
    }

}
