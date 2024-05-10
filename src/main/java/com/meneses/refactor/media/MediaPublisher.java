package com.meneses.refactor.media;

import java.util.HashMap;

public class MediaPublisher {
    private final HashMap<String, MediaSubscriber> subscribers = new HashMap<>();

    public void notifyChanges(byte[] bytes) {
        for (MediaSubscriber subscriber: subscribers.values()) {
            subscriber.updateBuffer(bytes);
        }
    }

    public void subscribe(MediaSubscriber subscriber) {
        String subscriberKey = subscriber.getClass().getSimpleName();
        if (!subscribers.containsKey(subscriberKey)) {
            subscribers.put(subscriberKey, subscriber);
        }
    }

    public void unsubscribe(MediaSubscriber subscriber) {
        String subscriberKey = subscriber.getClass().getSimpleName();
        subscribers.remove(subscriberKey);
    }
}
