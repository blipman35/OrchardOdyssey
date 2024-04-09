package com.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.event.KeyEvent;

public class TestKeyInput {

    private KeyInput keyInput;

    @BeforeEach
    public void setUp() {
        keyInput = new KeyInput();
    }

    @Test
    public void testKeyTyped() {
        assert true;
    }

    @Test
    public void testKeyPressed() {
        KeyEvent wEvent = new KeyEvent(new java.awt.Button(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        keyInput.keyPressed(wEvent);
        assertTrue(keyInput.upPressed);
    }

    @Test
    public void testKeyReleased() {
        KeyEvent wEvent = new KeyEvent(new java.awt.Button(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        keyInput.keyReleased(wEvent);
        assertFalse(keyInput.upPressed);
    }
}
