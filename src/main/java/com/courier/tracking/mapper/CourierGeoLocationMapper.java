package com.courier.tracking.mapper;

import com.courier.tracking.entity.CourierGeoLocation;
import com.courier.tracking.model.request.CourierGeolocationRequest;

public interface CourierGeoLocationMapper {

    CourierGeoLocation mapToCourierGeoLocation(CourierGeolocationRequest request);
}
