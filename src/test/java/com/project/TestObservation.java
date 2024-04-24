package com.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestObservation {

    private GameScreen gamescreen;

    @BeforeEach
    public void setUp() {gamescreen = GameScreen.getInstance();}

    @Test
    public void testAttach() {
        Observer observer = new Observer("All events");
        gamescreen.attach(observer, List.of(EventType.All));
    }

    @Test
    public void testDetach() {
        Observer observer = new Observer("All events");
        gamescreen.attach(observer, List.of(EventType.All));
        gamescreen.detach(observer);
    }

    @Test
    public void testNotify() {
        Observer observer = new Observer("All events");
        gamescreen.attach(observer, List.of(EventType.All));
        gamescreen.notifyObservers(EventType.All, "Test");
    }
}
