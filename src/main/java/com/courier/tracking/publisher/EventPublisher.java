package com.courier.tracking.publisher;

public interface EventPublisher<T> {

    void publishEvent(T event);
}
