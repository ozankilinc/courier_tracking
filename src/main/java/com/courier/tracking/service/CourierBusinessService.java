package com.courier.tracking.service;

import com.courier.tracking.model.request.CourierGeolocationRequest;

import java.util.List;

public interface CourierBusinessService {

    void processCourierGeolocations(List<CourierGeolocationRequest> courierGeolocationRequestList);

    void processCourierGeolocation(CourierGeolocationRequest courierGeolocationRequest);
}
