package com.courier.tracking.service.business;

import com.courier.tracking.event.CourierGeoLocationEvent;
import com.courier.tracking.mapper.CourierGeoLocationEventMapper;
import com.courier.tracking.model.request.CourierGeolocationRequest;
import com.courier.tracking.publisher.impl.CourierGeoLocationEventPublisher;
import com.courier.tracking.service.CourierBusinessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CourierBusinessServiceImpl implements CourierBusinessService {

    private final CourierGeoLocationEventMapper courierGeoLocationEventMapper;
    private final CourierGeoLocationEventPublisher courierGeoLocationEventPublisher;

    @Async
    @Override
    public void processCourierGeolocations(List<CourierGeolocationRequest> courierGeolocationRequestList) {
        List<CourierGeoLocationEvent> eventList = courierGeoLocationEventMapper.mapToList(courierGeolocationRequestList);
        eventList.forEach(courierGeoLocationEventPublisher::publishEvent);
    }

    @Async
    @Override
    public void processCourierGeolocation(CourierGeolocationRequest courierGeolocationRequest) {

    }

    private void test(List<CourierGeolocationRequest> courierGeolocationRequestList) {
        log.info("Before The List Process Data Size: " + courierGeolocationRequestList.size());
        List<CourierGeoLocationEvent> eventList = courierGeoLocationEventMapper.mapToList(courierGeolocationRequestList);
        log.info("After The List Process Data Size: " + eventList.size());
        eventList.forEach(a -> log.info(a.toString()));
    }
}
