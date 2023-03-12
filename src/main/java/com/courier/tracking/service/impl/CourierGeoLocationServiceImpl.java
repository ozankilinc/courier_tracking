package com.courier.tracking.service.impl;

import com.courier.tracking.entity.CourierGeoLocation;
import com.courier.tracking.repository.CourierGeoLocationRepository;
import com.courier.tracking.service.CourierGeoLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierGeoLocationServiceImpl implements CourierGeoLocationService {

    private final CourierGeoLocationRepository courierGeoLocationRepository;

    @Override
    public CourierGeoLocation saveCourierGeoLocation(CourierGeoLocation courierGeoLocation) {
        return courierGeoLocationRepository.save(courierGeoLocation);
    }
}
