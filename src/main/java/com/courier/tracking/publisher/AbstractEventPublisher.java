package com.courier.tracking.publisher;

import org.springframework.context.ApplicationEventPublisher;

public abstract class AbstractEventPublisher {

    protected final ApplicationEventPublisher applicationEventPublisher;

    protected AbstractEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
