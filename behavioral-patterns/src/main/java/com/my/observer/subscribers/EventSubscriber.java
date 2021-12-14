package com.my.observer.subscribers;

public interface EventSubscriber {

    void getNotification(EventType eventType, String message);
}
