package com.project;

import java.util.List;

public interface IObservable {

    void attach(IObserver observer, List<EventType> interestingEvents);
    void detach(IObserver observer);
    void notifyObservers(EventType eventType, String eventDescription);
}
