package com.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class TestPlayer {

    private Player player;
    private GameScreen gameScreen;
    private KeyInput keyInput;

    @BeforeEach
    void setUp() {
        gameScreen = GameScreen.getInstance();
        keyInput = new KeyInput();
        player = new Player.Builder(gameScreen, keyInput).setX(100).setY(288).build();
    }

    @Test
    void playerInitialization() {
        assertNotNull(player, "Player should not be null after initialization");
        assertEquals(100, player.getX(), "X coordinate should be initialized correctly");
        assertEquals(288, player.getY(), "Y coordinate should be initialized correctly");
    }

    @Test
    void playerAcceleratesCorrectly() {
        double initialSpeedY = player.getSpeedY();
        player.accelerate(10);  // Accelerate by 10 units
        assertEquals(initialSpeedY + 10, player.getSpeedY(), "Speed Y should increase by the acceleration amount");
    }

    @Test
    void playerMovesCorrectly() {
        double initialY = player.getY();
        player.move(5);
        assertEquals(initialY + 5, player.getY(), "Y coordinate should update correctly after move");
    }

    @Test
    void playerUpdates() {
        int initialX = player.getX();
        int initialY = player.getY();
        player.update();
        assertTrue(player.getX() == initialX && player.getY() <= initialY, "Player should not move horizontally and should only move vertically down or stay in the same vertical position after update");
    }

    @Test
    void playerDies() {
        player.die();
        assertFalse(player.isAlive, "Player should be set to not alive after calling die()");
    }

    @Test
    void playerBoundsCorrect() {
        assertNotNull(player.getBounds(), "Bounding box should not be null");
    }

    @Test
    void playerJumps() {
        keyInput.upPressed = true;
        player.update();  // Simulate a jump
        assertTrue(player.isJumping, "Player should be jumping after the jump command");
        keyInput.upPressed = false;
    }

    @Test
    void playerCrouches() {
        keyInput.downPressed = true;
        player.update();  // Simulate a crouch
        assertTrue(player.isCrouching, "Player should be crouching after the crouch command");
        keyInput.downPressed = false;
    }

    @Test
    void testPlayerDraw() {
        assertDoesNotThrow(() -> {
            BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D = bufferedImage.createGraphics();
            player.draw(graphics2D);
        }, "Draw method should not throw any exceptions");
    }
}
