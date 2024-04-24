package com.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestObserver {

    @Test
    public void testObserver() {
        Observer observer = new Observer("Test");
        observer.update(EventType.All, "Test event");
    }

    @Test
    public void testGetLastEventDescription() {
        Observer observer = new Observer("Test");
        assertEquals("No events received", observer.getLastEventDescription());
    }

}
