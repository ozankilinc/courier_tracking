package com.courier.tracking.listener;

import com.courier.tracking.event.CourierGeoLocationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CourierGeoLocationEventListener {

    @EventListener
    public void listenEvent(CourierGeoLocationEvent event) {
        log.info(event.toString());
    }
}
