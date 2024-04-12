package com.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TestGround {

    private Ground ground;

    @BeforeEach
    public void setUp() {
        ground = new Ground(4);
    }

    @Test
    public void testGround() {
        assertNotNull(ground);
    }

    @Test
    public void testUpdate() {
        ground.update();
    }
}
