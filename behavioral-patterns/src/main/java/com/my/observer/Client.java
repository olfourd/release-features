package com.my.observer;

import com.my.observer.subscribers.EmailSubscriber;
import com.my.observer.subscribers.EventSubscriber;
import com.my.observer.subscribers.SmsSubscriber;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        Store store = new Store();

        List<EventSubscriber> subscribers = List.of(
                new EmailSubscriber("piy.piy@gmail.com"),
                new EmailSubscriber("cyborg@gmail.com"),
                new SmsSubscriber("322-322-322"),
                new SmsSubscriber("322-322-323"),
                new SmsSubscriber("322-322-324")
        );

        subscribers.forEach(store::subscribeToAllNotifications);

        store.notifyNewProducts();
        store.notifySales();
        store.notifyClosed();
    }
}
