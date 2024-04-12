package com.project;

import javax.swing.*;
import java.awt.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TestGameScreen {

    private GameScreen gameScreen;

    @BeforeEach
    public void setUp() { gameScreen = new GameScreen(); }

    @Test
    public void testGameScreen() { assertNotNull(gameScreen); }

    @Test
    public void testStartGameThread() { gameScreen.startGameThread();
    }

    @Test
    public void testRun() { gameScreen.run(); }

    @Test
    public void testUpdate() { gameScreen.update(); }

    @Test
    public void testPaintComponent() {
        assert true;
    }


}
