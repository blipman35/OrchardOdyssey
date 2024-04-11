package com.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;

public class TestFruits {

    private Fruits fruits;

    @BeforeEach
    public void setUp() { fruits = new Fruits(0); }

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
        fruits.hasCollidedFruit();
    }

    @Test
    public void testResume() {
        fruits.resume();
    }
}
