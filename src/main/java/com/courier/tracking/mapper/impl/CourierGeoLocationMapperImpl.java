package com.courier.tracking.mapper.impl;

import com.courier.tracking.entity.CourierGeoLocation;
import com.courier.tracking.mapper.CourierGeoLocationMapper;
import com.courier.tracking.model.request.CourierGeolocationRequest;
import org.springframework.stereotype.Component;

@Component
public class CourierGeoLocationMapperImpl implements CourierGeoLocationMapper {
    @Override
    public CourierGeoLocation mapToCourierGeoLocation(CourierGeolocationRequest request) {
        return CourierGeoLocation.builder()
                .courierId(request.getCourierId())
                .time(request.getTime())
                .lat(request.getLat())
                .lng(request.getLng())
                .build();
    }
}
