package com.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TestHighScoreManager {

    private HighScoreManager highScoreManager;
    @BeforeEach
    public void setUp() { highScoreManager = HighScoreManager.getInstance(); }

    @Test
    public void testSingleton() {
        HighScoreManager instance2 = HighScoreManager.getInstance();
        assertNotNull(highScoreManager);
        assertSame(highScoreManager, instance2);
    }

    @Test
    public void testGetHighScore() {
        assertNotNull(highScoreManager.getHighScore());
    }

}
