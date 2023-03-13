package com.courier.tracking.service;

import com.courier.tracking.model.dto.CourierLegDto;
import com.courier.tracking.model.request.CourierGeolocationRequest;

public interface CourierLegService {

    CourierLegDto saveCourierLeg(CourierGeolocationRequest request, CourierLegDto courierLegDto);

    CourierLegDto findByCourierId(String courierId);

    Double getTotalTravelDistance(String courierId);
}
