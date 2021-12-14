package com.my.observer.subscribers;

public record EmailSubscriber(String email) implements EventSubscriber {

    @Override
    public void getNotification(EventType eventType, String message) {
        System.out.printf("Message \"%s\" was sent to email: %s. Type notification: %s%n",
                message, email, eventType);
    }
}
