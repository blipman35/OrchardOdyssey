package com.project;

import javax.swing.*;
import java.awt.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TestGameScreen {

    @Test
    public void testGameScreen() {
        GameScreen gameScreen = new GameScreen();
        assertNotNull(gameScreen);
    }

    @Test
    public void testStartGameThread() {
        GameScreen gameScreen = new GameScreen();
        gameScreen.startGameThread();
    }

    @Test
    public void testRun() {
        GameScreen gameScreen = new GameScreen();
        gameScreen.run();
    }

    @Test
    public void testUpdate() {
        GameScreen gameScreen = new GameScreen();
        gameScreen.update();
    }

    @Test
    public void testPaintComponent() {
        assert true;
    }


}
