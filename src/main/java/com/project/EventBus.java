package com.project;

import java.util.*;

public class EventBus {
    private static EventBus instance;
    private Map<EventType, List<IObserver>> observers;

    private EventBus() {
        observers = new EnumMap<>(EventType.class);
        for (EventType eventType : EventType.values()) {
            observers.put(eventType, new ArrayList<>());
        }
    }

    public static synchronized EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    public void attach(IObserver observer, EventType eventType) {
        if (eventType == EventType.All) {
            for(EventType type: EventType.values()) {
                if (type != EventType.All) {
                    List<IObserver> observerList = observers.get(type);
                    observerList.add(observer);
                }
            }
        }
        else {
            List<IObserver> observerList = observers.get(eventType);
            observerList.add(observer);
        }
    }

    public void postMessage(EventType eventType, String eventDescription) {
        List<IObserver> observerList = observers.get(eventType);
        for (IObserver observer : observerList) {
            observer.update(eventType, eventDescription);
        }
    }
}
