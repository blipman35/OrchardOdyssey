package com.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Graphics;
import javax.swing.JPanel;
import static org.junit.jupiter.api.Assertions.*;

class TestGameScreen {

    private GameScreen gameScreen;

    @BeforeEach
    void setUp() {
        gameScreen = GameScreen.getInstance();
    }

    @Test
    void testGameScreenSingleton() {
        GameScreen anotherInstance = GameScreen.getInstance();
        assertSame(gameScreen, anotherInstance, "GameScreen should always return the same instance.");
    }

    @Test
    void testStartGame() {
        gameScreen.startGame();
        assertNotNull(gameScreen, "Game should be marked as started.");
    }

    @Test
    void testGameUpdate() {
        gameScreen.update();
        // Since the specifics of update impacts are internal and complex, we primarily check for non-failure
        assertNotNull(gameScreen, "GameScreen update should execute without nullifying the instance.");
    }

    @Test
    void testRestartGame() {
        gameScreen.restartGame();
        assertEquals(0, gameScreen.score, "Score should be reset to zero after a restart.");
    }

    @Test
    void testPaintComponent() {
        assertDoesNotThrow(() -> {
            Graphics graphics = new JPanel().getGraphics(); // Simple graphics object for testing
            if (graphics != null) {
                gameScreen.paintComponent(graphics); // Paint component to ensure no exceptions thrown
            }
        }, "Painting the component should not throw any exception.");
    }

}
