package com.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;

public class TestFruits {

    private Fruits fruits;
    private Player player;

    @BeforeEach
    public void setUp() {
        GameScreen gs = new GameScreen();
        KeyInput ki = new KeyInput();
        player = new Player(gs, ki);
        player.setDefaultValues();
        fruits = new Fruits(100);
    }

    @Test
    public void testFruits() {
        assertNotNull(fruits);
    }

    @Test
    public void testUpdate() {
        assert true;
    }

    @Test
    public void testCreate() {
        assert true;
    }

    @Test
    public void testHasCollidedFruit() {
        assertFalse(fruits.hasCollidedFruit(player));
    }
    @Test
    public void testResume() {
        fruits.resume();
    }
}