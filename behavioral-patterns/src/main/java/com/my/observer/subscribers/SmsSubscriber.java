package com.my.observer.subscribers;

public record SmsSubscriber(String number) implements EventSubscriber {

    @Override
    public void getNotification(EventType eventType, String message) {
        System.out.printf("Message \"%s\" was sent to number: %s. Type notification: %s%n",
                message, number, eventType);
    }
}
