package com.my.observer.publisher;

import com.my.observer.subscribers.EventSubscriber;
import com.my.observer.subscribers.EventType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@NoArgsConstructor
public class EventPublisher {

    private Map<EventType, List<EventSubscriber>> subscribers;

    {
        subscribers = Arrays.stream(EventType.values())
                .collect(Collectors.toMap(Function.identity(), eventType -> new ArrayList<>()));
    }

    public void subscribe(@NonNull EventType eventType, @NonNull EventSubscriber eventListener) {
        subscribers.get(eventType).add(eventListener);
    }

    public void unsubscribe(@NonNull EventType eventType, @NonNull EventSubscriber eventListener) {
        subscribers.get(eventType).remove(eventListener);
    }

    public void notify(@NonNull EventType eventType, String message) {
        subscribers.get(eventType)
                .forEach(eventSubscriber -> eventSubscriber.getNotification(eventType, message));
    }
}
