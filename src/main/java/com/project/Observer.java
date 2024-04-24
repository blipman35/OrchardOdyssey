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

    @Override
    public void update(EventType event, String eventDescription) {
        System.out.println("Observer " + name + " received event " + event + " with description: " + eventDescription);
        if (events.contains(EventType.All) || events.contains(event)) {
            messages.add(eventDescription);
        }
    }

    public String getLastEventDescription() {
        if (messages.size() == 0) {
            return "No events received";
        }
        return messages.get(messages.size() - 1);
    }
}
