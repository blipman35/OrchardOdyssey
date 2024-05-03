package com.project;

import java.awt.*;
import java.util.*;

public class Observer implements IObserver{
    private String name;

    private ArrayList<EventType> events;

    private ArrayList<String> messages;

    public Observer(String name) {
        this.name = name;
        this.events = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    // Print out the event and its description to the console
    // If the event is in the list of events to observe, add the description to the messages list
    @Override
    public void update(EventType event, String eventDescription) {
        System.out.println("Observer " + name + " received event " + event + " with description: " + eventDescription);
        if (events.contains(EventType.All) || events.contains(event)) {
            messages.add(eventDescription);
        }
    }

    // Function to get the past events for logging purposes
    public String getLastEventDescription() {
        if (messages.size() == 0) {
            return "No events received";
        }
        return messages.get(messages.size() - 1);
    }
}
