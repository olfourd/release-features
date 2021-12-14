package com.my.observer;

import com.my.observer.publisher.EventPublisher;
import com.my.observer.subscribers.EventSubscriber;
import com.my.observer.subscribers.EventType;
import java.util.HashMap;
import java.util.Map;

public class Store {

    private final EventPublisher eventPublisher;
    private Map<String, Long> products;

    {
        eventPublisher = new EventPublisher();
        products = new HashMap<>();
    }

    public void notifySales() {
        eventPublisher.notify(EventType.SALE, "YAY! Free products for 3 kopeiki");
    }

    public void notifyClosed() {
        eventPublisher.notify(EventType.CLOSED, "Sorry! We'll have been closing for 2 days from NewYear");
    }

    public void notifyNewProducts() {
        eventPublisher.notify(EventType.NEW_PRODUCTS, "Hey! We have new products");
    }

    public void subscribeToAllNotifications(EventSubscriber eventSubscriber) {
        eventPublisher.subscribe(EventType.SALE, eventSubscriber);
        eventPublisher.subscribe(EventType.CLOSED, eventSubscriber);
        eventPublisher.subscribe(EventType.NEW_PRODUCTS, eventSubscriber);
    }

    private void unsubscribeToAllNotifications(EventSubscriber eventSubscriber) {
        eventPublisher.unsubscribe(EventType.SALE, eventSubscriber);
        eventPublisher.unsubscribe(EventType.CLOSED, eventSubscriber);
        eventPublisher.unsubscribe(EventType.NEW_PRODUCTS, eventSubscriber);
    }
}
