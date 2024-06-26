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
        GameScreen gs = GameScreen.getInstance();
        KeyInput ki = new KeyInput();
        player = new Player.Builder(gs, ki).setX(100).setY(300).build();
        fruits = new Fruits(150);
    }

    @Test
    public void testFruits() {
        assertNotNull(fruits);
    }

    @Test
    public void testUpdate() {
        int speed = 10;
        fruits.update(speed);
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
