package com.courier.tracking.listener;

import com.courier.tracking.entity.CourierGeoLocation;
import com.courier.tracking.event.CourierGeoLocationEvent;
import com.courier.tracking.event.CourierLocationEvent;
import com.courier.tracking.model.dto.PointDto;
import com.courier.tracking.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class CourierGeoLocationEventListener {

    private final StoreService storeService;

    @EventListener
    public void listenEvent(CourierGeoLocationEvent event) {
        log.info(event.toString());
    }

    @EventListener
    public void listenEvent(CourierLocationEvent event) {
        CourierGeoLocation courierGeoLocation = event.getCourierGeoLocation();
        PointDto pointDto = buildPointByCourierGeoLocation(courierGeoLocation);
        List<String> storeNamesByPoint = storeService.getStoreNamesByPoint(pointDto);
    }

    private PointDto buildPointByCourierGeoLocation(CourierGeoLocation courierGeoLocation) {
        return PointDto.builder()
                .lng(courierGeoLocation.getLng())
                .lat(courierGeoLocation.getLat())
                .build();
    }
}
