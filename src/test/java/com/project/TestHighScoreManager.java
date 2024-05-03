package com.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TestHighScoreManager {

    private HighScoreManager highScoreManager;

    @BeforeEach
    public void setUp() { highScoreManager = new HighScoreManager(); }

    @Test
    public void testHighScoreManager() {
        assertNotNull(highScoreManager);
    }

    @Test
    public void testSaveHighScore() {
        int score = highScoreManager.getHighScore();
        highScoreManager.setHighScore(100);
        assertEquals(highScoreManager.getHighScore(), 100);
        highScoreManager.setHighScore(score); // set score back so it doesn't mess up the game
    }

}
