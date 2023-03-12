package com.courier.tracking.publisher.impl;

import com.courier.tracking.event.CourierGeoLocationEvent;
import com.courier.tracking.publisher.AbstractEventPublisher;
import com.courier.tracking.publisher.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CourierGeoLocationEventPublisher extends
        AbstractEventPublisher implements EventPublisher<CourierGeoLocationEvent> {

    @Autowired
    public CourierGeoLocationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    public void publishEvent(CourierGeoLocationEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
