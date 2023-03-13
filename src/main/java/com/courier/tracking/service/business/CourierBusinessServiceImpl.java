package com.courier.tracking.service.business;

import com.courier.tracking.event.CourierLocationEvent;
import com.courier.tracking.mapper.CourierLocationEventMapper;
import com.courier.tracking.model.dto.CourierLegDto;
import com.courier.tracking.model.request.CourierGeolocationRequest;
import com.courier.tracking.publisher.impl.CourierGeoLocationEventPublisher;
import com.courier.tracking.service.CourierBusinessService;
import com.courier.tracking.service.CourierLegService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CourierBusinessServiceImpl implements CourierBusinessService {

    private final CourierLegService courierLegService;
    private final CourierLocationEventMapper courierLocationEventMapper;
    private final CourierGeoLocationEventPublisher courierGeoLocationEventPublisher;


    @Async
    @Override
    public void processCourierGeolocations(List<CourierGeolocationRequest> courierGeolocationRequestList) {
        courierGeolocationRequestList.forEach(this::processCourierGeolocation);
    }

    @Async
    @Override
    public void processCourierGeolocation(CourierGeolocationRequest courierGeolocationRequest) {
        CourierLegDto courierLegDto = courierLegService.findByCourierId(courierGeolocationRequest.getCourierId());
        courierLegDto = courierLegService.saveCourierLeg(courierGeolocationRequest, courierLegDto);
        CourierLocationEvent courierLocationEvent = courierLocationEventMapper.mapToCourierLocationEvent(courierLegDto);
        courierGeoLocationEventPublisher.publishEvent(courierLocationEvent);
    }

    @Override
    public Double getTotalTravelDistance(String courierId) {
        return courierLegService.getTotalTravelDistance(courierId);
    }

}
