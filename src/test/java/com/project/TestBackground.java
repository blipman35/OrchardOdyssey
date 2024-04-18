package com.project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestBackground {

    @Test
    public void testBackground() {
        GameScreen background = GameScreen.getInstance();
        assertNotNull(background);
    }

}
