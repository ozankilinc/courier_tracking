package com.courier.tracking.listener;

import com.courier.tracking.event.CourierLocationEvent;
import com.courier.tracking.model.dto.CourierLegDto;
import com.courier.tracking.service.CourierLocationEventBusinessService;
import com.courier.tracking.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CourierGeoLocationEventListener {

    private final StoreService storeService;
    private final CourierLocationEventBusinessService courierLocationEventBusinessService;


    @EventListener
    public void listenEvent(CourierLocationEvent event) {
        log.info(event.toString());
        CourierLegDto courierLegDto = event.getCourierLegDto();
        String nearestStoreByPoint = storeService.getNearestStoreByPoint(courierLegDto.getCurrentPoint());
        courierLocationEventBusinessService.handleCourierLocationEvent(event, nearestStoreByPoint);

    }
}
